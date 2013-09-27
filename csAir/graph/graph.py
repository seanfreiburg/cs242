# coding=utf-8
__author__ = 'seanfreiburg'
from node import *
import sys

# Holds query functions and nodes
class Graph:
  def __init__(self, metros, routes):
    self.nodes = dict()
    self.build_nodes(self, metros)
    self.build_edges(self, routes)


  # Adds all nodes from data set
  # @param metros The array from the json encoding
  def build_nodes(self, _, metros):
    for metro in metros:
      self.nodes[metro['code']] = Node(metro)

  # Adds an edge to the node
  # @param route route array from json
  # @param src index of source data
  # @param dst index of destination data
  def add_edge_to_node(self, route,src,dst):
    node = self.nodes.get(route['ports'][src])
    node.add_edge(route['ports'][dst], route['distance'])

  # Adds all routes to the nodes
  # @param routes route array from json
  def build_edges(self, _, routes):
    for route in routes:
      self.add_edge_to_node(route,0,1)
      self.add_edge_to_node(route,1,0)

  # returns node based on code
  # @param code string of city code
  # @return the node that matches the city code
  def get_node_from_code(self, code):
    return self.nodes.get(code)
















