# coding=utf-8
from cmath import sqrt
import json
import sys
from priQueue import *
from copy import deepcopy

__author__ = 'seanfreiburg'


class GraphUtils:
  def __init__(self):


    '''
  The longest single flight in the network
  @return a triple of start code, end code, and distance
  '''

  def longest_flight(self, graph):
    max_distance = 0
    for key, node in graph.nodes.iteritems():
      for entry in node.edges:
        distance = entry.distance
        if distance > max_distance:
          max_distance = distance
          start = node.code
          end = entry.destination

    return (start, end, max_distance)

  '''
  the shortest single flight in the network
  @return a triple of start code, end code, and distance
  '''

  def shortest_flight(self, graph):

    min_distance = sys.maxint
    for key, node in graph.nodes.iteritems():
      for entry in node.edges:
        distance = entry.distance
        if distance < min_distance:
          min_distance = distance
          start = node.code
          end = entry.destination
    return (start, end, min_distance)

  # the average distance of all the flights in the network
  # @return the distince as a number
  def average_distance(self, graph):
    number_of_flights = 0
    sum = 0
    for key, node in graph.nodes.iteritems():
      for entry in node.edges:
        number_of_flights += 1
        sum += entry.distance
    return sum / number_of_flights

  # the biggest city (by population) served by CSAir
  # @return a tuple of the city code and population of the city
  def biggest_city(self, graph):
    max_pop = 0
    city_code = ''
    for key, node in graph.nodes.iteritems():
      if (max_pop < node.population):
        max_pop = node.population
        city_code = node.code
    return (city_code, max_pop)

  # the smallest city (by population) served by CSAir
  # @return a tuple of the city code and population of the city
  def smallest_city(self, graph):
    min_pop = sys.maxint
    city_code = ''
    for key, node in graph.nodes.iteritems():
      if (min_pop > node.population):
        min_pop = node.population
        city_code = node.code

    return (city_code, min_pop)

  # the average size (by population) of all the cities served by CSAir
  # @return a number of the average
  def average_city(self, graph):
    number_of_cities = 0
    sum = 0
    for key, node in graph.nodes.iteritems():
      number_of_cities += 1
      sum += node.population

    return sum / number_of_cities

  # a list of the continents served by CSAir and which cities are in them
  # @return a dict of continents as keys and city name in array as values
  def get_continents_and_cities(self, graph):
    continents_dict = dict()

    for key, node in graph.nodes.iteritems():
      city_arr = continents_dict.get(node.continent)
      if city_arr is None:
        continents_dict[node.continent] = [node.name]
      else:
        continents_dict[node.continent].append(node.name)
    return continents_dict

  # identifying CSAir's hub cities – the cities that have the most direct connections.
  # @return an array of city codes
  def get_hub_cities(self, graph):
    node_codes = []
    max_length = 0
    for key, node in graph.nodes.iteritems():
      if (len(node.edges) > max_length):
        node_codes = [node.code]
        max_length = len(node.edges)
      elif (len(node.edges) == max_length):
        node_codes.append(node.code)
    return node_codes


  # Gets the map string to add to the url
  # Should probably be abstracted
  # @return the map url sting to attach to the base url
  def get_map_string(self, graph):

    map_string = ''
    for key, node in graph.nodes.iteritems():
      for entry in node.edges:
        if (map_string == ''):
          map_string += node.code + '-' + entry.destination
        else:
          map_string += ',+' + node.code + '-' + entry.destination

    return map_string

  # Returns array of all city names and codes
  # @return array of all city names and codes
  def get_cities(self, graph):
    cities = []
    for key, node in graph.nodes.iteritems():
      cities.append((node.code, node.name))
    return cities

  def save_to_disk(self, graph):
    data = dict()
    data['metros'] = []
    for key, value in graph.nodes.iteritems():
      data['metros'].append(
        {'code': value.code, 'name': value.name, 'country': value.country, 'continent': value.continent,
         'timezone': value.timezone, 'coordinates': value.coordinates, 'population': value.population,
         'region': value.region})
    data['routes'] = []
    for key, value in graph.nodes.iteritems():
      for edge in value.edges:
        data['routes'].append({'ports': [value.code, edge.destination], 'distance': edge.distance})

    string_data = json.dumps(data)
    f = open('assets/data/map_data_written.json', 'w')
    f.write(string_data)
    return

  def route_distance(self, graph, cities):
    length = len(cities)
    i = 0
    distance = []
    while i < length - 1:
      node = graph.nodes.get(cities[i])
      if node is None: return None
      in_edge = False
      for edge in node.edges:
        if cities[i + 1] == edge.destination:
          in_edge = True
          distance.append(edge.distance)
          break
      if not in_edge: return None
      i += 1
    return distance

  def route_cost(self, graph, cities, distances):
    if distances == None: return 0
    base = 0.40
    cost = 0
    dist = list(distances)
    while dist:
      base = base - .05
      if (base < 0):
        base = 0.0
      leg = dist.pop(0)
      leg_cost = (base ) * leg
      cost += leg_cost
    return cost


  def route_time(self, graph, cities, distances):
    time = 0.0
    i = 1
    a = 1406.25
    vf = 750.0
    for leg in distances:
      starting = leg / 2.0
      if starting > 200.0:
        starting = 200.0
        cruising = leg - 2*starting
        time += 2*(vf / a) + (cruising / (vf))
      else:
        time += sqrt((2.0 * starting)/a)*2.0
      if (len(distances) != i):
        layover_time = 2.0 - ((1.0 / 6.0) * (len(graph.nodes[cities[i - 1]].edges) -1))
        if (layover_time < 0.0): layover_time = 0.0
        time += layover_time
      i += 1
    return time

  def route_info(self, graph, cities):
    info = {}
    info['distance'] = self.route_distance(graph, cities)
    info['cost'] = self.route_cost(graph, cities, info['distance'])
    info['time'] = self.route_time(graph, cities, info['distance'])
    return info


  def dijkstra(self, graph, start, target=None):
    distances = dict()
    predecessors = dict()
    queue = priorityDictionary()
    queue[start] = 0

    for vertex in queue:
      distances[vertex] = queue[vertex]
      if vertex == target:
        break
      for edge in graph.nodes[vertex].edges:
        minLength = distances[vertex] + edge.distance
        if edge.destination in distances:
          pass
        elif edge.destination not in queue or minLength < queue[edge.destination]:
          queue[edge.destination] = minLength
          predecessors[edge.destination] = vertex

    return (distances, predecessors)

  def shortestPath(self, graph, start, target):
    _, predecessors = self.dijkstra(graph, start, target)
    path = []
    while True:
      path.append(target)
      if target == start:
        break
      target = predecessors[target]
    path.reverse()
    return path






