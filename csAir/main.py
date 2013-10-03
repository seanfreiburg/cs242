# Main console execution path
__author__ = 'seanfreiburg'

from graph.graph import Graph
from graph.graph_utils import GraphUtils
from graph.view import View
import json

view = View()
files = ['assets/data/map_data.json', 'assets/data/cmi_hub.json']
g = Graph()
for file in files:
  f = open(file, 'r')
  decoded = json.loads(f.read())
  g.build_nodes(decoded['metros'])
  g.build_edges(decoded['routes'])
utils = GraphUtils()
view.print_menu()
while (True):
  view.print_prompt_user()
  code = raw_input()
  if (code == '0' or code == 'exit'):
    exit(0)
  elif (code == '1'):
    #@todo fix this
    view.city_info(g)
  elif (code == '2'):
    longest_flight = utils.longest_flight(g)
    view.print_flight(longest_flight)
  elif (code == '3'):
    shortest_flight = utils.shortest_flight(g)
    view.print_flight(shortest_flight)
  elif (code == '4'):
    map_string = utils.get_map_string(g)
    view.display_map(map_string)
  elif (code == '5'):
    average_flight_distance = utils.average_distance(g)
    view.print_average_flight_distance(average_flight_distance)
  elif (code == '6'):
    largest_population = utils.biggest_city(g)
    view.print_population(largest_population)
  elif (code == '7'):
    smallest_population = utils.smallest_city(g)
    view.print_population(smallest_population)
  elif (code == '8'):
    average_population = utils.average_city(g)
    view.print_population_number(average_population)
  elif (code == '9'):
    continents_dict = utils.get_continents_and_cities(g)
    view.print_continents_and_cities(continents_dict)
  elif (code == '10'):
    hubs = utils.get_hub_cities(g)
    view.print_hub_cities(hubs)
  elif (code == '11'):
    cities = utils.get_cities(g)
    view.print_cities(cities)
  elif (code == '12'):
    # add a city
    data = view.add_city_menu()
    g.add_node(data)
  elif (code == '13'):
    data = view.add_route_menu()
    g.add_route(data['src'], data['dst'], data['distance'])
    # add a route
    pass
  elif (code == '14'):
    # remove a city
    code = view.remove_city_menu()
    g.remove_node(code)
  elif (code == '15'):
    # remove a route
    data = view.remove_route_menu()
    g.remove_route(data['src'], data['dst'])

  elif (code == '16'):
    # edit a city
    data = view.edit_city_menu()
    g.edit_node(data)

  elif (code == '17'):
    # save to disk
    utils.save_to_disk(g)
    # @todo prints errors success
  elif (code == '18'):
    cities = view.route_menu()
    route_return = utils.route_info(g, cities)
    view.print_route_info(route_return)
  elif (code == '19'):
    cities = view.route_menu()
    route = utils.shortestPath(g, cities[0], cities[1])
    view.print_route(route)
    route_return = utils.route_info(g, route)
    view.print_route_info(route_return)
  elif (code == '20'):
    view.print_menu()

  else:
    view.print_error()



