require 'net/http'
require 'json'
require 'open-uri'


CODE_EM_URI = '0.0.0.0'

player1_key = '55123172e0e107acaaf08c88b77e11e5'
player2_key = 'a41dd2fb6597bb629c29455afb2edead'
player3_key = '5b2caf4c6a9711cfbe2f36563c7cf64e'


players = ['55123172e0e107acaaf08c88b77e11e5','a41dd2fb6597bb629c29455afb2edead','5b2caf4c6a9711cfbe2f36563c7cf64e']


master_key = 'boobs'

uri = URI(CODE_EM_URI)
http = Net::HTTP.new(CODE_EM_URI,8080)
response = http.get('/open_tournament/'+master_key)
puts response.body

for player_key in players
  response = http.get( '/get_status/'+player_key)
  puts response.body
end

response = http.get( '/start_tournament/'+master_key)
puts response.body




10.times do
  for player_key in players
    response = JSON.parse(http.get( '/get_status/'+player_key).body)
    puts response
    if response['your_turn']
      response = http.get( '/send_action_and_amount/'+player_key +'?move=call')
      puts response.body
    end
  end
end






