require "./Deck.rb"
require "rubygems"
require "ruby-poker"
class Table

	#Creates a table object given a playerid Array
	def initialize(playeridArray)
		@playerArray = playeridArray
		@deck = Deck.new()
		@playerhands = Hash.new()
		@communitycards = []
		
		@deck.shuffleCards
	end

	#Deals cards to numPlayers players
	def dealCards()
		
		if  @playerArray.length > 8
			puts "Too many players for Texas Hold'em."
			return nil
		end
		
		for player in @playerArray
			@playerhands[player] = @deck.drawCards(2)
		end
	end

	#Returns the community cards
	def getCommunityCards
		return @communitycards
	end

	#Draws three cards for the flop 
	def flop()
		@communitycards += @deck.drawCards(3)
	end

	#Draws one card for the turn
	def turn()
		@communitycards += @deck.drawCards(1)
	end

	#Draws one card for the river
	def river()
		@communitycards += @deck.drawCards(1)
	end
end

t = Table.new([1,2,3,4,5])
t.dealCards()
print t.getCommunityCards
t.flop
print t.getCommunityCards
t.turn
print t.getCommunityCards
t.river
print t.getCommunityCards