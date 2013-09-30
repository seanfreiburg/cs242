__author__ = 'seanfreiburg'

import webbrowser
import os


class View:
  def __init__(self):
    return

  def print_menu(self):
    print("\nEnter a number for query\n[0] exit\n[1] city info\n"
          "[2] longest flight\n[3] shortest flight\n[4] graph url\n[5] average flight distance \n["
          "6] Biggest pop\n[7] Smallest pop\n[8] Average pop\n[9] Continents and cities\n"
          "[10] Hub cities\n[11] List all cities")

  # this is bad
  def print_city_info(self, graph, code):

    node = graph.get_node_from_code(code)
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


  # this is bad
  def city_info(self, graph):
    print("Enter a country code for data about it: ")
    code = raw_input()
    self.print_city_info(graph, code)

  def print_flight(self, flight):
    print('Start: ' + flight[0] + ', End: ' + flight[1] + ', Distance: ' + str(flight[2]))

  def display_map(self, map_string):
    url = 'http://www.gcmap.com/mapui?P=' + map_string
    webbrowser.open(url, new=2)

  def print_average_flight_distance(self, average_flight_distance):
    print('Average flight distance:  ' + str(average_flight_distance))

  def print_population(self, population):
    print('Biggest pop:  ' + population[0] + ' ' + str(population[1]) )

  def print_population_number(self, population):
    print('Population:  ' + str(population))

  def print_continents_and_cities(self, continents_dict):
    for key, arr in continents_dict.iteritems():
      print(key + ':')
      for entry in arr:
        print(entry )

  def print_hub_cities(self, hubs):
    for hub in hubs:
      print(hub + ', ')
    print('\n')

  def print_cities(self, cities):
    for entry in cities:
      print( entry[1] + ', Code: ' + entry[0])

  def print_error(self):
    print("I'm sorry, Dave, I'm afraid I can't do that.")
    #os.system("say \"I'm sorry, Dave, I'm afraid I can't do that.\"")

  def add_city_menu(self,graph):

    data = dict()
    print('Enter City Code: \n')
    data['code'] = raw_input()
    print('Enter City Name: \n')
    data['name'] = raw_input()
    print('Enter Country: \n')
    data['country'] = raw_input()
    print('Enter Continent: \n')
    data['continent'] = raw_input()
    print('Enter Timezone: \n')
    data['timezone'] = raw_input()
    print('Enter Coordinates: \n')
    data['coordinates'] = raw_input()
    print('Enter Population: \n')
    data['population'] = raw_input()
    print('Enter Region: \n')
    data['region'] = raw_input()
    graph.add_node(data)
    return


  def add_route_menu(self,graph):
    print('Enter source city code: \n')
    src = raw_input()
    print('Enter destination code: \n')
    dst = raw_input()
    print('Enter distance: \n')
    distance = raw_input()
    graph.add_route(src, dst, distance)
    return


  def remove_city_menu(self,graph):
    print('Enter city code to remove')
    code = raw_input()
    graph.remove_node(code)
    return


  def remove_route_menu(self,graph):
    print('Enter source city code: \n')
    src = raw_input()
    print('Enter destination code: \n')
    dst = raw_input()
    graph.remove_route(src, dst)
    return


  def edit_city_menu(self,graph):
    data = dict()
    print('Enter City Code: \n')
    data['code'] = raw_input()
    print('Enter City Name: \n')
    data['name'] = raw_input()
    print('Enter Country: \n')
    data['country'] = raw_input()
    print('Enter Continent: \n')
    data['continent'] = raw_input()
    print('Enter Timezone: \n')
    data['timezone'] = raw_input()
    print('Enter Coordinates: \n')
    data['coordinates'] = raw_input()
    print('Enter Population: \n')
    data['population'] = raw_input()
    print('Enter Region: \n')
    data['region'] = raw_input()
    graph.edit_node(data)
    return


