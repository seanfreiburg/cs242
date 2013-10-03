__author__ = 'seanfreiburg'

import webbrowser


class View:
  def __init__(self):
    return

  def print_menu(self):
    print("\nEnter a number for query\n[0] exit\n[1] city info\n"
          "[2] longest flight\n[3] shortest flight\n[4] graph url\n[5] average flight distance \n["
          "6] Biggest pop\n[7] Smallest pop\n[8] Average pop\n[9] Continents and cities\n"
          "[10] Hub cities\n[11] List all cities\n[12] Add a city\n[13] Add Route\n[14]"
          " Remove city\n[15] Remove route\n[16] Edit city\n[17] Save to disk\n[18] Route Info\n"
          "[19] Shortest Path\n[20] View Menu")

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
    print('Pop:  ' + population[0] + ' ' + str(population[1]) )

  def print_population_number(self, population):
    print('Population:  ' + str(population))

  def print_continents_and_cities(self, continents_dict):
    for key, arr in continents_dict.iteritems():
      print(key + ':')
      for entry in arr:
        print(entry )

  def print_hub_cities(self, hubs):
    i = 0
    for hub in hubs:
      i += 1
      if (i == len(hubs)):
        print(hub)
      else:
        print(hub + ', ')
    print('\n')

  def print_cities(self, cities):
    for entry in cities:
      print( entry[1] + ', Code: ' + entry[0])

  def print_error(self):
    print("I'm sorry, Dave, I'm afraid I can't do that.")
    #os.system("say \"I'm sorry, Dave, I'm afraid I can't do that.\"")

  def add_city_menu(self):

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
    data['population'] = get_positive_number()
    print('Enter Region: \n')
    data['region'] = raw_input()
    return data


  def add_route_menu(self):
    data = dict()
    print('Enter source city code: \n')
    data['src'] = raw_input()
    print('Enter destination code: \n')
    data['dst'] = raw_input()
    print('Enter distance: \n')
    data['distance'] = get_positive_number()
    return data


  def remove_city_menu(self):
    print('Enter city code to remove')
    code = raw_input()

    return code


  def remove_route_menu(self):
    print('Enter source city code: \n')
    src = raw_input()
    print('Enter destination code: \n')
    dst = raw_input()
    return {'src': src, 'dst': dst}


  def edit_city_menu(self):
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
    data['population'] = get_positive_number()
    print('Enter Region: \n')
    data['region'] = raw_input()

    return data

  def route_menu(self):
    print('Enter the city codes: \n')
    cities = raw_input()
    return cities.split(',')

  def print_route_info(self, route_return):
    print('Total Distance: ' + str(sum(route_return['distance'])))
    print('Cost: ' + str(route_return['cost']))
    print('Time in Hours: ' + str(route_return['time']))

  def print_route(self, route):
    for stop in route:
      print(stop + " "),
    print('\n')

  def print_prompt_user(self):
    print('Enter a menu code: ')

  def print_success(self):
    print('Saved\n')


def get_positive_number():
  num = -1
  while num < 0:
    num = int(raw_input())
    if (num < 0):
      print('Please enter a positive number: \n')
  return num

