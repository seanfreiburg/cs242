require_relative 'game'

START_MONEY = 1000
START_PLAYERS = 4

game = Game.new(options= {debug: true})
players = []

for i in 0..START_PLAYERS
  players << Player.new(i,START_MONEY)
end

game.shuffle_up_and_deal(players)









