require_relative 'table'

def round(table)
  table.printTable
  for player in table.playerArray
    i=0
    puts table.playerHands[player]
    puts
    puts table.communityCards
    action,amount = gets.chomp.split(' ')
    if action == 'fold'
      table.fold(player)
    elsif action == 'call'
      table.call(player)
    elsif action == 'raise'
      table.raise(player,amount.to_i)
    elsif action == 'printTable'
      table.printTable
    else
      puts 'Invalid action, folding'
    end

    table.printTable

  end
end


puts 'Enter number of players'
#num_players = gets.chomp.to_i
num_players = 6

if num_players < 2
  puts 'Must enter 2 or more players'
  exit(1)
end

table = Table.new((1..num_players).to_a)

table.dealCards
round(table)
table.flop
round(table)
table.turn
round(table)
table.river
round(table)

puts table.playerHands
puts table.communityCards
puts table.determineWinner.id
table.printTable








