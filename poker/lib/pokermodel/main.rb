require_relative 'table'
puts 'Enter number of players'
#num_players = gets.chomp.to_i
num_players = 6

if num_players < 2
  puts 'Must enter 2 or more players'
  exit(1)
end

table = Table.new((1..num_players).to_a)

table.dealCards
table.flop
table.turn
table.river

puts table.playerHands
puts table.communityCards
puts table.determineWinner.id
table.printTable






