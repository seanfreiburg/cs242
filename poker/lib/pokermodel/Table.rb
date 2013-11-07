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
		cardArray = @deck.getCards

		if  @playerArray.length > 8
			puts "Too many players for Texas Hold'em."
			return nil
		end
		
		for player in @playerArray
			@playerhands[player] = cardArray.pop(2)
		end
			print @playerhands

	end

end

t = Table.new([1,2,3,4,5])
t.dealCards()