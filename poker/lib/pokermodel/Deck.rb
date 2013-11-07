
class Deck

	#Create all the strings that represent cards
	def initialize()
		@cards=[]
		for value in ((2..9).map{|x| x.to_s}+['T','J','Q','K','A'])
			for suit in ['S','C','H','D']
				@cards += [value+suit]
			end
		end
	end

	#Shuffle the cards in the deck so they aren't in order.
	def shuffleCards()
		@cards.shuffle!
	end

	#Print the card array (used for Debugging)
	def printCardArray()
		print @cards
	end

	#Returns the array of cards.
	def getCards()
		return @cards
	end

end

