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
    self.biggest = None
    self.smallest = None
    self.average = None
    self.con_n_cities = None
    self.hubs = None

    self.build_nodes(self, metros)
    self.build_edges(self, routes)


  def build_nodes(self, _, metros):
    for metro in metros:
      self.nodes[metro['code']] = Node(metro['code'], metro['name'], metro['country'], metro['continent']
        , metro['timezone'], 0, metro['population'], metro['region'])


  def build_edges(self, _, routes):
    for route in routes:
      node = self.nodes.get(route['ports'][0])
      node.add_edge(route['ports'][1], route['distance'])

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
    else:
      return self.longest_dist

      # the shortest single flight in the network

  def shortest_flight(self):
    if (self.longest_dist is None):

      min_distance = sys.maxint
      for key, node in self.nodes.iteritems():
        for entry in node.edges:
          distance = entry.distance
          if distance < min_distance:
            min_distance = distance
            start = node.code
            end = entry.destination

      self.longest_dist = (start, end, min_distance)
      return self.longest_dist
    else:
      return self.longest_dist

    # the average distance of all the flights in the network
  def average_distance(self):
    return

    # the biggest city (by population) served by CSAir
  def biggest_city(self):
    return

    # the smallest city (by population) served by CSAir
  def smallest_city(self):
    return

    # the average size (by population) of all the cities served by CSAir
  def average_city(self):
    return

    # a list of the continents served by CSAir and which cities are in them
  def continents_and_cities(self):
    return

    # identifying CSAir's hub cities – the cities that have the most direct connections.
  def hub_cities(self):
    return


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

















