require "rubygems"
require "ruby-poker"
require_relative 'player'
require_relative 'deck'
class Table
  STARTING_MONEY = 1000
  attr_reader :communityCards
  #Creates a table object given a playerid Array
  def initialize(playerIdArray)
    @playerArray = []
    for playerId in playerIdArray
        @playerArray << Player.new(playerId, STARTING_MONEY)
    end
    @deck = Deck.new()
    @playerHands = Hash.new()
    @communityCards = []

    @deck.shuffleCards
  end

  #Deals cards to numPlayers players
  def dealCards()

    if  @playerArray.length > 8
      puts "Too many players for Texas Hold'em."
      return nil
    end

    for player in @playerArray
      @playerHands[player] = @deck.drawCards(2)
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
    besthand = PokerHand.new(@playerHands[winner]+@communityCards)

    for player in @playerArray
      hand = PokerHand.new(@playerHands[player]+@communityCards)
      if hand > besthand
        besthand = hand
        winner = player
      end
    end
    winner
  end

  #Prints the player id's with thier hands and Community Cards.
  def printTable
    puts
    for player in @playerArray
      puts
      print "Player "+player.to_s+" => "
      print @playerHands[player]
    end
    puts
    puts
    print @communityCards
  end

  def removeBrokePLayers
    for player in @playerArray
      if player.money < 0
        @playerArray.delete(player)
      end
    end
  end

end

