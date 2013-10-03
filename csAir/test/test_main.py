from graph.controller import Controller
from graph.graph import Graph

__author__ = 'seanfreiburg'
from unittest import TestCase
from graph.graph_utils import GraphUtils


class TestGraphUtils(TestCase):
  def setUp(self):
    controller = Controller()
    files = ['assets/data/map_data.json', 'assets/data/cmi_hub.json']
    self.g = Graph()
    controller.build_graph_from_files(self.g, files)
    self.utils = GraphUtils()

  def testMerge(self):
    print(self.utils.smallest_city(self.g))
    assert self.utils.smallest_city(self.g)[0] == u'CMI'

