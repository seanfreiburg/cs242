from unittest import TestCase

__author__ = 'seanfreiburg'
from graph import graph
import json


class TestFlightGraph(TestCase):
  def setUp(self):
    f = open('../assets/data/map_data.json', 'r')
    decoded = json.loads(f.read())
    self.g = graph.Graph(decoded['metros'], decoded['routes'])

  def testInit(self):
    assert (self.g.nodes.get('ALG').name == 'Algiers')

  def testEdgeDestinations(self):
    assert (self.g.nodes.get('CHI').get_destinations() == ['YYZ', 'ATL'])






