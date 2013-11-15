require 'net/http'
require 'json'
require 'open-uri'
require 'securerandom'


CODE_EM_URI = '0.0.0.0'

player1_key = '4ab5d8d5e86460d95eabdfec0ae5c7f9'
player2_key = 'ea390b7be8ad721d57d002c7fddb448d'
player3_key = '44b687ad88176d550048cba96bb5395f'


players = [player1_key, player2_key, player3_key]


master_key = 'boobs'

uri = URI(CODE_EM_URI)
http = Net::HTTP.new(CODE_EM_URI, 3000)
response = http.get('/open_tournament'+'?key='+ master_key)
puts response.body

for player_key in players
  response = http.get('/get_status'+'?key='+player_key)
  puts response.body
end

response = http.get('/start_tournament'+'?key='+master_key)
puts response.body


loop {
  for player_key in players
    response = JSON.parse(http.get('/get_status'+'?key='+player_key).body)
    puts response
    break if !response['active']
    if response['your_turn']
      response = http.get('/send_move'+'?key='+player_key +'&move=call')
      puts response.body
    end
    #sleep 1
  end
}

puts response['winner']






