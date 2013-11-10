require_relative 'table'
puts 'Enter number of players'
#num_players = gets.chomp.to_i
num_players = 6

if num_players < 2
  puts 'Must enter 2 or more players'
  exit(1)
end

table = Table.new(1..num_players)

table.dealCards
table.flop

puts table.communityCards






