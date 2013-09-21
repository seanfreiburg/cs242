__author__ = 'seanfreiburg'
from graph import graph
import pprint

import json

f = open('assets/data/map_data.json', 'r')
decoded = json.loads(f.read())
g = graph.Graph(decoded['metros'], decoded['routes'])


def print_city_info(code):
  global node, dest_and_dists, item
  node = g.get_node(code)
  if (node is None):
    print('Code not found\n')
  else:
    print('Code: ' + node.code)
    print('Name: ' + node.name)
    print('Country: ' + node.country)
    print('Continent: ' + node.continent)
    print('Timezone: ' + str(node.timezone))
    print('Latitude: ' + str(node.latitude))
    print('Longitude: ' + str(node.longitude))
    print('Population: ' + str(node.population))
    print('Region: ' + str(node.region))
    dest_and_dists = node.edges
    for item in dest_and_dists:
      print('Destination code: ' + item.destination + ' distance: ' + str(item.distance))


def city_info(code):
  print("Enter a country code for data about it: ")
  code = raw_input()
  print_city_info(code)


while(True):
  print("Enter a number for query\n[0] exit\n[1] city info\n[2] longest flight\n[3] shortest flight\n")
  code = raw_input()
  if(code == '0'):
    exit()
  elif(code == '1'):
    city_info(code)
  elif(code == '2'):
    longest = g.longest_flight()
    print('Start: '+ longest[0] + ',2 End: ' + longest[1] + ', Distance: ' + str(longest[2]))
  elif(code == '3'):
    shortest = g.shortest_flight()
    print('Start: '+ shortest[0] + ',2 End: ' + shortest[1] + ', Distance: ' + str(shortest[2]))
