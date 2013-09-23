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
    print('Coordinates: ' + str(node.coordinates))
    print('Population: ' + str(node.population))
    print('Region: ' + str(node.region))
    dest_and_dists = node.edges
    for item in dest_and_dists:
      print('Destination code: ' + item.destination + ' distance: ' + str(item.distance))


def city_info(code):
  print("Enter a country code for data about it: ")
  code = raw_input()
  print_city_info(code)


while (True):
  print("Enter a number for query\n[0] exit\n[1] city info\n[2] longest flight\n[3] shortest flight\n[4] graph url\n[5] average flight distance \n[6] Biggest pop\n[7] Smallest pop\n[8] Average pop\n[9] Continents and cities\n[9] Hub cities\n")
  code = raw_input()
  if (code == '0'):
    exit()
  elif (code == '1'):
    city_info(code)
  elif (code == '2'):
    longest = g.longest_flight()
    print('Start: ' + longest[0] + ',2 End: ' + longest[1] + ', Distance: ' + str(longest[2]))
  elif (code == '3'):
    shortest = g.shortest_flight()
    print('Start: ' + shortest[0] + ',2 End: ' + shortest[1] + ', Distance: ' + str(shortest[2]))
  elif(code=='4'):
    print('http://www.gcmap.com/mapui?P=' + g.get_map_string())
  elif(code=='5'):
    print('Average flight distance:  ' + str(g.average_distance()))

  elif(code=='6'):
    biggest = g.biggest_city()
    print('Biggest pop:  ' + biggest[0] + ' '+ str(biggest[1]) )
  elif(code=='7'):
    smallest = g.smallest_city()
    print('Smallest pop:  ' + smallest[0] + ' '+ str(smallest[1]) )
  elif(code=='8'):
    average = g.average_city()
    print('Average pop:  ' + average[0] + ' '+ str(average[1]) )
  elif(code=='9'):
    hubs = g.hub_cities()
    for hub in hubs:
      print(hub + ', ')
    print('\n')

