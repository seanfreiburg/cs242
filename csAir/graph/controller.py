# Main console execution path
__author__ = 'seanfreiburg'

import json


from graph import Graph
from graph_utils import GraphUtils
from view import View

class Controller:
  def __init__(self):
    return

  def build_graph_from_files(self, g, files):
    for file in files:
      f = open(file, 'r')
      decoded = json.loads(f.read())
      g.build_nodes(decoded['metros'])
      g.build_edges(decoded['routes'])


  def main(self):
    v = View()
    files = ['assets/data/map_data.json', 'assets/data/cmi_hub.json']
    g = Graph()
    self.build_graph_from_files(g, files)
    utils = GraphUtils()
    v.print_menu()
    while (True):
      v.print_prompt_user()
      code = raw_input()
      if (code == '0' or code == 'exit'):
        exit(0)
      elif (code == '1'):
        #@todo fix this
        v.city_info(g)
      elif (code == '2'):
        longest_flight = utils.longest_flight(g)
        v.print_flight(longest_flight)
      elif (code == '3'):
        shortest_flight = utils.shortest_flight(g)
        v.print_flight(shortest_flight)
      elif (code == '4'):
        map_string = utils.get_map_string(g)
        v.display_map(map_string)
      elif (code == '5'):
        average_flight_distance = utils.average_distance(g)
        v.print_average_flight_distance(average_flight_distance)
      elif (code == '6'):
        largest_population = utils.biggest_city(g)
        v.print_population(largest_population)
      elif (code == '7'):
        smallest_population = utils.smallest_city(g)
        v.print_population(smallest_population)
      elif (code == '8'):
        average_population = utils.average_city(g)
        v.print_population_number(average_population)
      elif (code == '9'):
        continents_dict = utils.get_continents_and_cities(g)
        v.print_continents_and_cities(continents_dict)
      elif (code == '10'):
        hubs = utils.get_hub_cities(g)
        v.print_hub_cities(hubs)
      elif (code == '11'):
        cities = utils.get_cities(g)
        v.print_cities(cities)
      elif (code == '12'):
        # add a city
        data = v.add_city_menu()
        g.add_node(data)
      elif (code == '13'):
        data = v.add_route_menu()
        g.add_route(data['src'], data['dst'], data['distance'])
        # add a route
        pass
      elif (code == '14'):
        # remove a city
        code = v.remove_city_menu()
        g.remove_node(code)
      elif (code == '15'):
        # remove a route
        data = v.remove_route_menu()
        g.remove_route(data['src'], data['dst'])

      elif (code == '16'):
        # edit a city
        data = v.edit_city_menu()
        g.edit_node(data)

      elif (code == '17'):
        # save to disk
        utils.save_to_disk(g)
        # @todo prints errors success
      elif (code == '18'):
        cities = v.route_menu()
        route_return = utils.route_info(g, cities)
        v.print_route_info(route_return)
      elif (code == '19'):
        cities = v.route_menu()
        route = utils.shortestPath(g, cities[0], cities[1])
        v.print_route(route)
        route_return = utils.route_info(g, route)
        v.print_route_info(route_return)
      elif (code == '20'):
        v.print_menu()

      else:
        v.print_error()

