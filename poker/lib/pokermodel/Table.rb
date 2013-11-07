require "./Deck.rb"
class Table


	def initialize()
		@deck = Deck.new()
		@deck.shuffleCards
	end
	#Deals cards to numPlayers players
	def dealCards(numPlayers)
		if  2*numPlayers > (16)
			puts "Too many players for Texas Hold'em."
		end
		cardArray = @deck.getCards
		print cardArray
	end

end

t = Table.new()
t.dealCards(2)