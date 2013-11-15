require 'net/http'
require 'json'
require 'open-uri'
require 'securerandom'


CODE_EM_URI = '0.0.0.0'

player1_key = '776564ba0d8a6cbd081671db5e320670'
player2_key = 'c58ede57ba39e07809b9298fbf2bb826'
player3_key = '88f4839b5bbaee0e7f07eb3908002e39'


players = [player1_key, player2_key, player3_key]


master_key = 'boobs'

uri = URI(CODE_EM_URI)
http = Net::HTTP.new(CODE_EM_URI, 3000)
response = http.get('/open_tournament/'+master_key)
puts response.body

for player_key in players
  response = http.get('/get_status/'+player_key)
  puts response.body
end

response = http.get('/start_tournament/'+master_key)
puts response.body


loop {
  for player_key in players
    response = JSON.parse(http.get('/get_status/'+player_key).body)
    puts response
    break if response['winner']
    if response['your_turn']
      response = http.get('/send_action_and_amount/'+player_key +'?move=call')
      puts response.body
    end
  end
}

puts response['winner']






