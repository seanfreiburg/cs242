require "./Table.rb"

class PokerTest
	table = Table.new([1,2,3,4])
	table.dealCards()
	describe "Flop" do
		it "should draw three cards." do
			table.flop	
		 	table.getCommunityCards.length.should eq(3)
		end
	end

	describe "Turn" do
		it "should draw one card." do
			table.turn	
		 	table.getCommunityCards.length.should eq(4)
		end
	end

	describe "River" do
		it "should draw one card." do
			table.river
		 	table.getCommunityCards.length.should eq(5)
		end
	end

	describe "determineWinner" do
		it "should find the winner of the hand." do
			puts
			puts
			print "Winner: "+table.determineWinner.to_s
			table.printTable
		end
	end

end