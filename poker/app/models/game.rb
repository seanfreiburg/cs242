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


  attr_accessor :community_cards, :deck, :active_players, :players, :current_bets, :stacks
  attr_reader :MASTER_API_KEY


  def initialize
    @open = false
    @started = false
    @debug = true
    @players = []
    @active_players = []
    @winners = []
    @pot = 0
    @ante = STARTING_ANTE
    @small_blind = nil
    @big_blind = nil
    @current_high_bet = 0
    @current_bets = Hash.new
    @turn = nil
    @MASTER_API_KEY = API_KEY
    @deck = Deck.new
    @community_cards = []
    @hands = Hash.new
    @game_over = false
    @stacks = Hash.new
    @overall_winner = nil

  end

  def game_status(player)
    if @game_over
      game_over_status(player)
    elsif @open
      open_status(player)
    elsif @started
      started_status(player)
    else
      closed_status(player)
    end
  end

  def game_over_status(player)
    {winner: @overall_winner.name, active: false}
  end

  def open_status(player)
    @players << player unless @players.any? { |a| a.id == player.id || @players.size > 8}
    {players: @players.map { |a| a.name }, active: false}
  end

  def started_status(player)
    {community_cards: @community_cards, your_turn: player == @turn, hand: @hands[player], big_blind: @big_blind.name, small_blind: @small_blind.name,
     dealer: @dealer.name, money: @stacks[player], ante: @ante, active: true}
  end

  def closed_status(player)
    {error: 'Game is not open', active: false}
  end

  def accept_bets
    while @active_players.size > 1
      for player in @active_players
        puts "...action to #{player.id}: #{player.name}" if @debug
        @turn = player
        action, amount = get_action
        @turn = nil
        if action == 'fold'
          fold(player)
        elsif action == 'raise' || action == 'bet'
          bet(player, amount)
        elsif action == 'call'
          call(player)
        else
          puts "Invalid action, folding, action was #{action}" if @debug
          fold(player)
        end

        if @active_players.size == 1
          @winners = @active_players
          throw :winner
        end
      end
      break unless @current_bets.values.uniq.size > 1
    end

  end

  def fold(player)
    @active_players.delete(player)
    @current_bets.delete(player)
    puts "#{player.id}: #{player.name} folds" if @debug
  end

  def call(player)
    bet_action(player, @current_high_bet - @current_bets[player])
  end

  def bet(player, amount)
    if amount < @ante
      fold(player)
    else
      bet_action(player, amount)
    end
  end

  def bet_action(player, amount)
    @stacks[player] -= amount
    @pot += amount
    @current_bets[player] += amount
    @current_high_bet = amount if amount > @current_high_bet
    puts "#{player.id}: #{player.name} bets $#{amount}" if @debug
  end

  #Determines the winner of a game
  def determineWinner
    winner = @players.first
    bestHand = PokerHand.new(@hands[winner]+@community_cards)

    for player in @players
      hand = PokerHand.new(@hands[player]+@community_cards)
      if hand > bestHand
        bestHand = hand
        winner = player
      end
    end
    @winners = [winner]
  end

  def deal
    catch :winner do
      @community_cards = []
      @dealer = @players.shift
      @players << @dealer
      @ante = STARTING_ANTE
      @current_high_bet = @ante
      @current_bets = Hash.new
      @pot = 0
      @active_players = @players

      for player in @players
        @hands[player] = []
        @current_bets[player] = 0
      end

      if @players.size <= 2
        @small_blind = @dealer
        @big_blind = @players.first
      else
        @small_blind = @players.first
        @big_blind = @players[1]

      end

      bet_action(@small_blind, @ante / 2)
      bet_action(@big_blind, @ante)

      @deck = Deck.new

      for player in @players
        @hands[player] = @deck.drawCards!(2)
      end


      if @debug
        puts "\nNEW GAME:"
        @players.each do |p|
          if p == @dealer
            puts "D #{p.id} #{@hands[player]} $#{@stacks[p]}"
          elsif p == @small_blind
            puts "B #{p.id} #{@hands[player]} $#{@stacks[p]}"
          elsif p == @big_blind
            puts "BB #{p.id} #{@hands[player]} $#{@stacks[p]}"
          else
            puts " #{p.id} #{@hands[player]} $#{@stacks[p]}"
          end
        end
      end


      # shift if game count is 0 so the person after small blind is first
      if  @players.size > 2
        2.times do
          player = @active_players.shift
          @active_players << player
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
    #binding.pry
  end

  def distributePot
    for winner in @winners
      @stacks[winner] += @pot/@winners.size
    end
  end

  #Draws three cards for the flop
  def flop
    @community_cards += @deck.drawCards!(3)

    if @debug
      puts "the flop..."
      @community_cards.each { |c| puts c }
    end
  end

  #Draws one card for the turn
  def turn
    @community_cards += @deck.drawCards!(1)

    if @debug
      puts "the turn..."
      puts @community_cards.last
    end
  end

  #Draws one card for the river
  def river
    @community_cards += @deck.drawCards!(1)

    if @debug
      puts "the river..."
      puts @community_cards.last
    end
  end

  def shuffle_up_and_deal
    @players = @players.shuffle
    for player in @players
      @stacks[player] = STARTING_MONEY
    end
    until @game_over
      deal
      remove_broke_players
      if @players.size == 1
        @game_over = true
      end
    end
    puts 'Game Over' if @debug
    @overall_winner = @players.first

  end

  def open_tournament
    if @open
      return 'tournament is already open'
    end
    @open = true
    @started = false
    self.reset
    'tournament is now open'

  end

  def start_tournament
    @started = true
    @open = false
    shuffle_up_and_deal
  end

  def reset
    @community_cards = []
    @players = []
    @active_players = []
    @debug = Hash.new
    @winners = []
    @deck = Deck.new
    @pot = 0
    @ante = 0
    @small_blind = nil
    @big_blind = nil
    @current_high_bet = 0
    @current_bets = Hash.new
    @turn = nil
  end

  def get_action
    @action = nil
    @amount = nil
    while @action.nil?

    end
    [@action, @amount]
  end

  def set_action(action, amount)
    #@todo do validations here so player can get feedback
    message, action,amount = validate_action(action, amount)
    @action = action
    @amount = amount
    message
  end

  def validate_action(action, amount)
    if action == 'fold'
      validate_fold
    elsif action == 'raise' || action == 'bet'
      validate_bet(amount)
    elsif action == 'call'
      validate_call
    else
      puts "Invalid action, folding, action was #{action}" if @debug
      [{message: 'Action was invalid, folding'}, 'fold']
    end
  end

  def validate_fold
    [{message: 'Folding'}, 'fold',0]
  end

  def validate_bet(amount)
    if amount < @ante
      [{message: 'Bet was invalid, folding'}, 'fold',0]
    elsif amount >= @stacks[@turn]
      [{message: "Going all in! Betting #{amount}"}, 'bet', @stacks[@turn]]

    else
      [{message: "Betting #{amount}"}, 'bet',amount]
    end
  end

  def validate_call
    diff = @current_high_bet - @current_bets[@turn]
    if @stacks[@turn] - diff < 0
      [{message: 'Call was invalid, folding'}, 'fold',0]
    elsif  @stacks[@turn] - diff == 0
      [{message: "Going all in! Calling #{diff}"}, 'call',0]
    else
      [{message: "Calling #{diff}"}, 'call',0]
    end
  end


  def remove_broke_players
    puts 'removeBrokePlayers'
    for player in @players
      if @stacks[player] <= 0
        @players.delete(player)
        puts 'im nuts'.red
        puts @stacks[player].red if @debug
      end
    end
  end

end