require "rubygems"
require "ruby-poker"

require_relative 'player'
require_relative 'deck'

class Game
  #https://github.com/jjulian/pitboss/blob/master/lib/pitboss/game.rb
  include Singleton

  STARTING_ANTE = 2
  STARTING_MONEY = 1000
  API_KEY = 'boobs'


  attr_accessor :communityCards, :deck
  attr_reader :MASTER_API_KEY


  def initialize(options={})
    @open = false
    @started = false
    @debug = options[:debug]
    @players = []
    @activePlayers = []
    @winners = []
    @pot = 0
    @ante = STARTING_ANTE
    @smallBlind = nil
    @bigBlind = nil
    @currentHighBet = 0
    @currentBets = Hash.new
    @turn = nil
    @MASTER_API_KEY = API_KEY

  end

  def game_status(player)
    if @open
      unless @players.any? { |a| a.id == player.id }
        @players << player
      end
      p = []
      {players: @players.map { |a| a.name }, active: false}

    elsif @started

      # @todo return game data
      return {community_cards: @communityCards, your_turn: player == @turn, hand: player.hand }
    else
      return {error: 'Game is not open'}
    end
  end

  def accept_bets
    while @activePlayers.size > 1
      for player in @activePlayers
        puts "...action to #{player.id}" if @debug
        @turn = player
        action, amount = get_action
        @turn = nil
        if action == 'fold'
          fold(player)
        elsif action == 'bet'

          if amount < @ante
            fold(player)
          else
            bet(player, amount)
          end
        elsif action == 'call'
          call(player)
        else
          puts 'Invalid action, folding' if @debug
          fold(player)
        end

        if @activePlayers.size == 1
          @winners = @activePlayers
          throw :winner
        end
      end
      break unless @currentBets.values.uniq.size > 1
    end

  end

  def fold(player)
    @activePlayers.delete(player)
    @currentBets.delete(player)
    puts "#{player.id} folds" if @debug
  end

  def call(player)
    bet(player, @currentHighBet - @currentBets[player])
  end

  def bet(player, amount)
    return fold(player) if amount > player.money
    player.money -= amount
    @pot += amount
    @currentBets[player] += amount
    @currentHighBet = amount if amount > @currentHighBet
    puts "#{player.id} bets $#{amount}" if @debug
  end

  #Determines the winner of a game
  def determineWinner
    winner = @players.first
    bestHand = PokerHand.new(winner.hand+@communityCards)

    for player in @players
      hand = PokerHand.new(player.hand+@communityCards)
      if hand > bestHand
        bestHand = hand
        winner = player
      end
    end
    @winners = [winner]
  end

  def deal
    catch :winner do
      @communityCards = []
      @dealer = @players.shift
      @players << @dealer
      @ante = STARTING_ANTE
      @currentHighBet = @ante
      @currentBets = Hash.new
      @pot = 0
      @activePlayers = @players

      for player in @players
        player.clearHand
        @currentBets[player] = 0
      end

      if @players.size <= 2
        @smallBlind = @dealer
        @bigBlind = @players.first
      else
        @smallBlind = @players.first
        @bigBlind = @players[1]

      end

      bet(@smallBlind, @ante / 2)
      bet(@bigBlind, @ante)

      @deck = Deck.new

      for player in @players
        player.hand = @deck.drawCards!(2)
      end


      if @debug
        puts "\nNEW GAME:"
        @players.each do |p|
          if p == @dealer
            puts "D #{p.id} #{p.hand} $#{p.money}"
          elsif p == @smallBlind
            puts "B #{p.id} #{p.hand} $#{p.money}"
          elsif p == @bigBlind
            puts "BB #{p.id} #{p.hand} $#{p.money}"
          else
            puts " #{p.id} #{p.hand} $#{p.money}"
          end
        end
      end


      # shift if game count is 0 so the person after small blind is first
      if  @players.size > 2
        2.times do
          player = @activePlayers.shift
          @activePlayers << player
        end
      end

      accept_bets
      @deck.burn!(1)
      flop
      accept_bets
      @deck.burn!(1)
      turn
      accept_bets
      @deck.burn!(1)
      river
      accept_bets

      determineWinner
    end
    declareWinner
    distributePot
  end

  def declareWinner
    puts "#{@winners.map(&:id).join(' and ')} #{@winners.size == 1 ? 'is' : 'are'} the winner#{'s' unless @winners.size == 1}"
  end

  def distributePot
    for winner in @winners
      winner.money += @pot/@winners.size
    end
  end

  #Draws three cards for the flop
  def flop()
    @communityCards += @deck.drawCards!(3)

    if @debug
      puts "the flop..."
      @communityCards.each { |c| puts c }
    end
  end

  #Draws one card for the turn
  def turn()
    @communityCards += @deck.drawCards!(1)

    if @debug
      puts "the turn..."
      puts @communityCards.last
    end
  end

  #Draws one card for the river
  def river()
    @communityCards += @deck.drawCards!(1)

    if @debug
      puts "the river..."
      puts @communityCards.last
    end
  end

  def shuffle_up_and_deal
    @players = @players.shuffle
    for player in @players
      player.money = STARTING_MONEY
    end
    deal
  end

  def open_tournament
    if @open
      return 'tournament is already open'
    end
    @open = true
    @started = false
    self.reset_game
    'tournament is now open'

  end

  def start_tournament
    @started = true
    @open = false
    shuffle_up_and_deal
  end

  def reset_game
    @communityCards = []
    @players = []
    @activePlayers = []
    @debug = Hash.new
    @winners = []
    @deck = Deck.new
    @pot = 0
    @ante = 0
    @smallBlind = nil
    @bigBlind = nil
    @currentHighBet = 0
    @currentBets = Hash.new
    @turn = nil
  end

  def get_action
    @action = nil
    @amount = nil
    while @action.nil?

    end
    [@action, @amount]
  end

  def set_action(action,amount)
    #@todo do validations here so player can get feedback
    @action = action
    @amount = amount.to_i

  end

end