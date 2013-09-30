from unittest import TestCase
from graph.graph_utils import GraphUtils

__author__ = 'seanfreiburg'
from graph import graph
import json


class TestFlightGraph(TestCase):
  def setUp(self):
    f = open('../assets/data/map_data.json', 'r')
    decoded = json.loads(f.read())
    self.g = graph.Graph(decoded['metros'], decoded['routes'])
    self.utils = GraphUtils()

  def testInit(self):
    assert (self.g.nodes.get('ALG').name == 'Algiers')

  def testEdgeDestinations(self):
    assert (self.g.nodes.get('CHI').get_destinations() == ['MEX', 'LAX','SFO' ,'YYZ', 'ATL'])








