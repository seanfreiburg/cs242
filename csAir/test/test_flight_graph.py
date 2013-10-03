from unittest import TestCase
from graph.graph_utils import GraphUtils

__author__ = 'seanfreiburg'
from graph.graph import Graph
from graph.edge import Edge
import json


class TestFlightGraph(TestCase):
  def setUp(self):
    f = open('assets/data/map_data.json', 'r')
    decoded = json.loads(f.read())
    self.g = Graph()
    self.g.build_nodes(decoded['metros'])
    self.g.build_edges(decoded['routes'])
    self.utils = GraphUtils()

  def testInit(self):
    assert (self.g.nodes.get('ALG').name == 'Algiers')

  def testEdgeDestinations(self):
    assert (self.g.nodes.get('CHI').get_destinations() == ['MEX', 'LAX', 'SFO', 'YYZ', 'ATL'])

  def testAddCity(self):
    data = {'code': 'CBS', 'name': 'Test City', 'country': 'US', 'continent': 'Europe',
            'timezone': 0, 'coordinates': 'adssa', 'population': 3, 'region': 'south'}
    print(data)
    self.g.add_node(data)
    assert self.g.get_node_from_code('CBS').name == 'Test City'

  def testAddRoute(self):
    self.g.add_route('TYO', 'YYZ', 2000)
    node = self.g.get_node_from_code('TYO')
    e = Edge('YYZ', 2000)
    bool = False
    for edge in node.edges:
      if e.destination == edge.destination and e.distance == edge.distance:
        bool = True
        break
    assert bool == True

  def testRemoveRoute(self):
    self.g.add_route('TYO', 'YYZ', 2000)
    self.g.remove_route('TYO', 'YYZ')
    node = self.g.get_node_from_code('TYO')
    e = Edge('YYZ', 2000)
    bool = False
    for edge in node.edges:
      if e.destination == edge.destination and e.distance == edge.distance:
        bool = True
        break
    assert bool != True

  def testEditCity(self):
    data = {'code': 'CHI', 'name': 'Test City', 'country': 'US', 'continent': 'Europe',
            'timezone': 0, 'coordinates': 'adssa', 'population': 3, 'region': 'south'}
    self.g.edit_node(data)
    assert self.g.get_node_from_code('CHI').name == 'Test City'







