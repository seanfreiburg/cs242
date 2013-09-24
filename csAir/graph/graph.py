# coding=utf-8
__author__ = 'seanfreiburg'
from node import *
import sys


class Graph:
  def __init__(self, metros, routes):
    self.nodes = dict()
    self.longest_dist = None
    self.shortest_dist = None
    self.average_dist = None
    self.biggest_pop = None
    self.smallest_pop = None
    self.average_pop = None
    self.con_n_cities = None
    self.hubs = None

    self.build_nodes(self, metros)
    self.build_edges(self, routes)


  def build_nodes(self, _, metros):
    for metro in metros:
      self.nodes[metro['code']] = Node(metro['code'], metro['name'], metro['country'], metro['continent']
        , metro['timezone'], 0, metro['population'], metro['region'])


  def add_edge_to_node(self, route,src,dst):
    node = self.nodes.get(route['ports'][src])
    node.add_edge(route['ports'][dst], route['distance'])

  def build_edges(self, _, routes):
    for route in routes:
      self.add_edge_to_node(route,0,1)
      self.add_edge_to_node(route,1,0)

  def get_node(self, code):
    return self.nodes.get(code)

  # the longest single flight in the network
  def longest_flight(self):
    if (self.longest_dist is None):

      max_distance = 0
      for key, node in self.nodes.iteritems():
        for entry in node.edges:
          distance = entry.distance
          if distance > max_distance:
            max_distance = distance
            start = node.code
            end = entry.destination

      self.longest_dist = (start, end, max_distance)

    return self.longest_dist

      # the shortest single flight in the network

  def shortest_flight(self):
    if (self.shortest_dist is None):

      min_distance = sys.maxint
      for key, node in self.nodes.iteritems():
        for entry in node.edges:
          distance = entry.distance
          if distance < min_distance:
            min_distance = distance
            start = node.code
            end = entry.destination

      self.shortest_dist = (start, end, min_distance)

    return self.shortest_dist

    # the average distance of all the flights in the network
  def average_distance(self):
    if(self.average_dist == None):
      number_of_flights =0
      sum = 0
      for key, node in self.nodes.iteritems():
        for entry in node.edges:
          number_of_flights += 1
          sum += entry.distance
          self.average_dist = sum/number_of_flights
    return self.average_dist

    # the biggest city (by population) served by CSAir
  def biggest_city(self):
    if (self.biggest_pop == None):
      max_pop = 0
      city_code = ''
      for key, node in self.nodes.iteritems():
        if (max_pop < node.population):
          max_pop = node.population
          city_code = node.code
      self.biggest_pop = (city_code,max_pop)
    return self.biggest_pop

    # the smallest city (by population) served by CSAir
  def smallest_city(self):
    if (self.smallest_pop == None):
      min_pop = sys.maxint
      city_code = ''
      for key, node in self.nodes.iteritems():
        if (min_pop > node.population):
          min_pop = node.population
          city_code = node.code
      self.smallest_pop = (city_code,min_pop)
    return self.smallest_pop

    # the average size (by population) of all the cities served by CSAir
  def average_city(self):
    if(self.average_pop== None):
      number_of_cities =0
      sum = 0
      for key, node in self.nodes.iteritems():
        number_of_cities += 1
        sum += node.population
        self.average_pop = sum/number_of_cities
    return self.average_pop

    # a list of the continents served by CSAir and which cities are in them
  def continents_and_cities(self):
    continents_dict = dict()

    for key, node in self.nodes.iteritems():
      city_arr = continents_dict.get(node.continent)
      if city_arr is None:
        continents_dict[node.continent] = [node.name]
      else:
        continents_dict[node.continent].append(node.name)
    return continents_dict

    # identifying CSAir's hub cities – the cities that have the most direct connections.
  def hub_cities(self):
    node_codes = []
    max_length = 0
    for key, node in self.nodes.iteritems():
      if(len(node.edges) > max_length):
        node_codes = [node.code]
        max_length = len(node.edges)
      elif (len(node.edges) == max_length):
        node_codes.append(node.code)
    return node_codes


#http://www.gcmap.com/mapui?P=LIM-MEX,+LIM-BOG,+MEX-LAX
  def get_map_string(self):

    map_string = ''
    for key, node in self.nodes.iteritems():
        for entry in node.edges:
          if (map_string == ''):
            map_string += node.code + '-'+ entry.destination
          else:
            map_string += ',+'+ node.code + '-'+ entry.destination

    return map_string

  def get_cities(self):
    cities = []
    for key, node in self.nodes.iteritems():
      cities.append((node.code,node.name))
    return cities

















