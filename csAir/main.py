# Main console execution path
__author__ = 'seanfreiburg'


from graph.graph import Graph
from graph.graph_utils import GraphUtils
from graph.view import View
import json

view = View()
f = open('assets/data/map_data.json', 'r')
decoded = json.loads(f.read())
g = Graph(decoded['metros'], decoded['routes'])
utils = GraphUtils()

while (True):
  view.print_menu()
  code = raw_input()
  if (code == '0'):
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
  elif(code=='4'):
    map_string = utils.get_map_string(g)
    view.display_map(map_string)
  elif(code=='5'):
    average_flight_distance = utils.average_distance(g)
    view.print_average_flight_distance(average_flight_distance)
  elif(code=='6'):
    largest_population = utils.biggest_city(g)
    view.print_population(largest_population)
  elif(code=='7'):
    smallest_population = utils.smallest_city(g)
    view.print_population(smallest_population)
  elif(code=='8'):
    average_population = utils.average_city(g)
    view.print_population_number(average_population)
  elif(code=='9'):
    continents_dict = utils.get_continents_and_cities(g)
    view.print_continents_and_cities(continents_dict)
  elif(code=='10'):
    hubs = utils.get_hub_cities(g)
    view.print_hub_cities(hubs)
  elif(code=='11'):
    cities = utils.get_cities(g)
    view.print_cities(cities)
  elif(code=='12'):
    # add a city
    view.add_city_menu(g) #@todo fix
    pass
  elif(code=='13'):
    view.add_route_menu(g) #@todo fix
    # add a route
    pass
  elif(code=='14'):
    # remove a city
    view.remove_city_menu(g) # @todo fix
    pass
  elif(code=='15'):
    # remove a route
    data =view.remove_route_menu(g)
    g.remove_route(data['src'], data['dst'])
    pass
  elif(code=='16'):
    # edit a city
    data = view.edit_city_menu(g)
    g.edit_node(data)
    pass
  elif(code=='17'):
    # save to disk
    utils.save_to_disk(g)
    # @todo prints errors success
  elif(code=='18'):
    cities = view.route_menu()
    route_return = utils.route_info(cities)
    view.print_route_info(route_return)

  else:
    view.print_error()



