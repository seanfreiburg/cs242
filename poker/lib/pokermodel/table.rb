require "rubygems"
require "ruby-poker"
require_relative 'player'
require_relative 'deck'
class Table

  STARTING_MONEY = 1000
  STARTING_BLIND = 10
  attr_accessor :bet, :pot, :sidePot
  attr_reader :communityCards, :playerArray, :playerHands


  #Creates a table object given a playerid Array
  def initialize(playerIdArray)
    @playerArray = []
    @foldedPlayers = []
    for playerId in playerIdArray
      @playerArray << Player.new(playerId, STARTING_MONEY)
    end
    @deck = Deck.new()
    @playerHands = Hash.new()
    @communityCards = []
    @dealer = @playerArray.first
    @deck.shuffleCards!
    @bet = 0
    @pot =0

  end

  #Deals cards to numPlayers players
  def dealCards()

    if  @playerArray.length > 8
      puts "Too many players for Texas Hold'em."
      return nil
    end

    for player in @playerArray
      player.hand = @deck.drawCards!(2)
    end
  end



  #Determines the winner of a game
  def determineWinner
    winner = @playerArray[0]
    bestHand = PokerHand.new(winner.hand+@communityCards)

    for player in @playerArray
      hand = PokerHand.new(player.hand+@communityCards)
      if hand > bestHand
        bestHand = hand
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
    @foldedPlayers = @playerArray.delete(player)
  end

  def call(player)
    if player.money >= bet
      @pot += bet
      player.money -= bet
    else
      #@todo fix this
      self.fold(player)
    end
  end

  def raise(player, amount)
    if player.money >= bet + amount
      @bet += amount
      player.money -= bet+amount
    else
      #@todo fix this
      self.fold(player)
    end
  end

end

