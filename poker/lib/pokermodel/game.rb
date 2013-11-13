require "rubygems"
require "ruby-poker"

require_relative 'player'
require_relative 'deck'

class Game
  #https://github.com/jjulian/pitboss/blob/master/lib/pitboss/game.rb
  STARTING_ANTE = 2

  def initialize(options={})
    @debug = options[:debug]
  end

  def game_status(id)

  end

  def accept_bets
    while @activePlayers.size > 1
      for player in @activePlayers
        puts "...action to #{player.id}" if @debug
        action, amount = gets.chomp.split(' ')
        if action == 'fold'
          fold(player)
        elsif action == 'bet'

          if amount.to_i < @ante
            fold(player)
          else
            bet(player, amount.to_i)
          end
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

  def bet(player, amount)
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
    winner
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
          elsif p == @small_blind
            puts "B #{p.id} #{p.hand} $#{p.money}"
          elsif p == @big_blind
            puts "BB #{p.id} #{p.hand} $#{p.money}"
          else
            puts " #{p.id} #{p.hand} $#{p.money}"
          end
        end
      end


      # shift if game count is 0 so the person after small blind is first
      if @gameCount == 0 && @players.size > 2
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
    end

    declareWinner

  end

  def declareWinner
    puts "#{@winners.map(&:id).join(' and ')} #{@winners.size == 1 ? 'is' : 'are'} the winner#{'s' unless @winners.size == 1}! Beer for them!"
    #todo distribute @pot
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

  def shuffle_up_and_deal(players)
    @players = players.shuffle

    @count = 0

    deal

  end

end