require 'net/http'
require 'json'
require 'open-uri'
require 'colorize'
require 'securerandom'

# This code is a horrible mess. This is what a time crunch does to code

GAME_URI = '0.0.0.0'

def logic(turn_data)

  return 'call', nil

end


def get_hand(turn_data)
  card1, card2 = turn_data['hand']

end

def get_deal_action(turn_data)

  return logic(turn_data)

end

def get_flop_action(turn_data)
  community_cards = turn_data['community_cards']

  return logic(turn_data)
end

def get_turn_action(turn_data)
  community_cards = turn_data['community_cards']
  return logic(turn_data)
end

def get_river_action(turn_data)
  community_cards = turn_data['community_cards']
  return logic(turn_data)
end


def get_action(turn_data)
  if turn_data['community_cards'].empty?
    get_deal_action(turn_data)
  elsif turn_data['community_cards'].length == 3
    get_flop_action(turn_data)
  elsif turn_data['community_cards'].length == 4
    get_turn_action(turn_data)
  else
    get_river_action(turn_data)
  end
end

def get_game_state(http, player_key)
  response = http.get('/get_status'+'?key='+player_key)
  JSON.parse(response.body)
end

def get_api_key(http)
  response = http.get('/get_key'+'?name='+ SecureRandom.hex)
  puts 'hey'.red
  JSON.parse(response.body)['key']

end

def send_action(http, player_key, action, amount)
  response = http.get('/send_move'+'?key='+player_key +"&move=#{action}&amount=#{amount}")
  puts 'Response'.red
  puts response.body
  puts 'End Response \n'.red
end


def main

  http = Net::HTTP.new(GAME_URI, 3000)
  player_key = get_api_key(http)
  i =0
  loop {
    turn_data = get_game_state(http, player_key)
    break if turn_data['winner']
    puts 'Get Request '.green + i.to_s.green
    puts turn_data
    puts ' End Get Request \n'.green
    if turn_data['your_turn']
      action, amount = get_action(turn_data)
      puts puts 'Action'.blue
      puts action.yellow
      puts amount.yellow if amount
      puts 'End Action \n'.blue
      send_action(http, player_key, action, amount)
    end
    sleep 1
    i +=1
  }
end

#Byah
main