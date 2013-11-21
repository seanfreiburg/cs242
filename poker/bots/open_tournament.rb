require 'net/http'
require 'json'
require 'open-uri'
require 'securerandom'

master_key = 'boobs'

GAME_URI = '0.0.0.0'


http = Net::HTTP.new(GAME_URI, 3000)
response = http.get('/open_tournament'+'?key='+ master_key)
puts response.body