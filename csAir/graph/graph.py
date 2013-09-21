# coding=utf-8
__author__ = 'seanfreiburg'
from node import *


class Graph:
  def __init__(self, metros, routes):
    self.nodes = dict()
    self.build_nodes(self,metros)
    self.build_edges(self,routes)





  def build_nodes(self, _ , metros):
    for metro in metros:
      self.nodes[metro['code']] = Node( metro['code'], metro['name'], metro['country'], metro['continent']
        , metro['timezone'], 0, 0, metro['population'], metro['region'])


  def build_edges(self, _ , routes):
    for route in routes:
      node = self.nodes.get(route['ports'][0])
      node.add_edge(route['ports'][1], route['distance'])

  def get_node(self,code):
    return self.nodes.get(code)




    # the longest single flight in the network
    # the shortest single flight in the network
    # the average distance of all the flights in the network
    # the biggest city (by population) served by CSAir
    # the smallest city (by population) served by CSAir
    # the average size (by population) of all the cities served by CSAir
    # a list of the continents served by CSAir and which cities are in them
    # identifying CSAir's hub cities – the cities that have the most direct connections.






