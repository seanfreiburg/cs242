require "rubygems"
require "ruby-poker"
require_relative 'player'
require_relative 'deck'
class Table

  STARTING_MONEY = 1000
  STARTING_BLIND = 10
  attr_accessor  :bet, :pot,:sidePot
  attr_reader :communityCards, :playerArray, :playerHands


  #Creates a table object given a playerid Array
  def initialize(playerIdArray)
    @playerArray = []
    for playerId in playerIdArray
        @playerArray << Player.new(playerId, STARTING_MONEY)
    end
    @deck = Deck.new()
    @playerHands = Hash.new()
    @communityCards = []
    @dealer = @playerArray.first
    @deck.shuffleCards

  end

  #Deals cards to numPlayers players
  def dealCards()

    if  @playerArray.length > 8
      puts "Too many players for Texas Hold'em."
      return nil
    end

    for player in @playerArray
      player.hand = @deck.drawCards(2)
    end
  end

  #Draws three cards for the flop
  def flop()
    @communityCards += @deck.drawCards(3)
  end

  #Draws one card for the turn
  def turn()
    @communityCards += @deck.drawCards(1)
  end

  #Draws one card for the river
  def river()
    @communityCards += @deck.drawCards(1)
  end

  #Determines the winner of a game
  def determineWinner
    winner = @playerArray[0]
    besthand = PokerHand.new(winner.hand+@communityCards)

    for player in @playerArray
      hand = PokerHand.new(player.hand+@communityCards)
      if hand > besthand
        besthand = hand
        winner = player
      end
    end
    winner
  end

  #Prints the player id's with their hands and Community Cards.
  def printTable
    puts
    for player in @playerArray
      puts
      print "Player "+player.to_s+" => "
      print player.hand
    end
    puts
    puts
    print @communityCards
  end

  def removeBrokePlayers
    for player in @playerArray
      if player.money <= 0
        @playerArray.delete(player)
      end
    end
  end

  def fold(player)
    @playerArray.delete(player)
  end

  def call(player)

  end

  def raise(player,amount)

  end

end

