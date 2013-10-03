# coding=utf-8
__author__ = 'seanfreiburg'
from node import Node

# Holds query functions and nodes
class Graph:
  def __init__(self):
    self.nodes = dict()


  # Adds all nodes from data set
  # @param metros The array from the json encoding
  def build_nodes(self, metros):
    for metro in metros:
      self.nodes[metro['code']] = Node(metro)

  # Adds an edge to the node
  # @param route route array from json
  # @param src index of source data
  # @param dst index of destination data
  def add_edge_to_node(self, route, src, dst):
    node = self.nodes.get(route['ports'][src])
    node.add_edge(route['ports'][dst], route['distance'])

  # Adds all routes to the nodes
  # @param routes route array from json
  def build_edges(self, routes):
    for route in routes:
      self.add_edge_to_node(route, 0, 1)

  # returns node based on code
  # @param code string of city code
  # @return the node that matches the city code
  def get_node_from_code(self, code):
    return self.nodes.get(code)

  # add a node to the graph
  # @param data - dictionary of data to be added
  def add_node(self, data):
    if ( not self.nodes.get(data['code'])):
      self.nodes[data['code']] = Node(data)

  # removes a node and it's edges from the graph
  # @param code - the code of the node you want deleted
  def remove_node(self, code):
    if (self.nodes.get(code)):
      self.nodes.pop(code)
      self.remove_edges(code)

  # removes all edges in other nodes references the code
  # @param code - the code of the node you want deleted
  def remove_edges(self, code):
    for key, node in self.nodes.iteritems():
      i = 0
      for edge in node.edges:
        if (edge.destination == code):
          node.edges.pop(i)
        i += 1

  # removes a route from the graph
  # @param src - staring node code
  # @param dst - ending node code
  def remove_route(self, src, dst):
    node = self.nodes.get(src)
    if node is not None:
      i = 0
      for edge in node.edges:
        if edge.destination == dst:
          node.edges.pop(i)
        i += 1

  # adds a route from the graph
  # @param staring node code
  # @param ending node code
  # @param distance - int of distance of route
  def add_route(self, src, dst, distance):
    node = self.nodes.get(src)
    if node is not Node:
      node.add_edge(dst, distance)

  # wrapper around node edit
  # @param data - dictionary of data to be put in the edit
  def edit_node(self, data):
    node = self.nodes.get(data['code'])
    if node is not None:
      node.edit(data)




















