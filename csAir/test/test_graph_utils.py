__author__ = 'seanfreiburg'

from unittest import TestCase
from graph.graph_utils import GraphUtils

__author__ = 'seanfreiburg'
from graph import graph
import json


class TestGraphUtils(TestCase):
  def setUp(self):
    f = open('assets/data/map_data.json', 'r')
    decoded = json.loads(f.read())
    self.g = graph.Graph(decoded['metros'], decoded['routes'])
    self.utils = GraphUtils()

  def testSave(self):
    self.utils.save_to_disk(self.g)
    f2 = open('assets/data/map_data_written.json', 'r')
    decoded = json.loads(f2.read())
    self.g2 = graph.Graph(decoded['metros'], decoded['routes'])
    self.utils = GraphUtils()
    assert (self.utils.longest_flight(self.g2) == ('SYD', 'LAX', 12051))
    assert (self.utils.shortest_flight(self.g2) == ('NYC', 'WAS', 334))
    assert (self.utils.average_distance(self.g2) == 2300)
    assert self.utils.biggest_city(self.g2) == ('TYO', 34000000)
    assert self.utils.smallest_city(self.g2) == ( 'ESS', 589900)



  def testLongestFlight(self):
    assert (self.utils.longest_flight(self.g) == ('SYD', 'LAX', 12051))

  def testShortestFlight(self):
    assert (self.utils.shortest_flight(self.g) == ('NYC', 'WAS', 334))

  def testMapUrl(self):
    #assert(self.utils.get_map_string(self.g) == 'PAR-ESS,+PAR-MIL,+MIL-ESS,+MIL-IST,+MIA-WAS,+CCU-HKG,+CCU-BKK,+LIM-MEX,+LIM-BOG,+ATL-WAS,+ATL-MIA,+PEK-ICN,+LON-NYC,+LON-ESS,+LON-PAR,+IST-BGW,+LOS-KRT,+LOS-FIH,+CAI-ALG,+CAI-IST,+CAI-BGW,+CAI-RUH,+DEL-CCU,+DEL-MAA,+DEL-BOM,+BOM-MAA,+BGW-THR,+BGW-KHI,+BGW-RUH,+NYC-YYZ,+BOG-MIA,+BOG-SAO,+BOG-BUE,+SCL-LIM,+SAO-MAD,+SAO-LOS,+SFO-CHI,+JKT-SYD,+BKK-HKG,+BKK-SGN,+BKK-JKT,+KHI-DEL,+KHI-BOM,+MNL-SFO,+MNL-SYD,+MNL-SGN,+SGN-JKT,+OSA-TPE,+HKG-SHA,+HKG-TPE,+HKG-MNL,+HKG-SGN,+BUE-SAO,+TPE-MNL,+ESS-LED,+ICN-TYO,+CHI-YYZ,+CHI-ATL,+THR-DEL,+THR-KHI,+THR-RUH,+KRT-CAI,+SHA-PEK,+SHA-ICN,+SHA-TYO,+SHA-TPE,+FIH-KRT,+FIH-JNB,+WAS-YYZ,+WAS-NYC,+RUH-KHI,+TYO-SFO,+TYO-OSA,+LED-MOW,+LED-IST,+SYD-LAX,+ALG-MAD,+ALG-PAR,+ALG-IST,+MOW-THR,+MOW-IST,+MAA-CCU,+MAA-BKK,+MAA-JKT,+JNB-KRT,+LAX-SFO,+LAX-CHI,+MAD-NYC,+MAD-LON,+MAD-PAR,+MEX-LAX,+MEX-CHI,+MEX-MIA,+MEX-BOG')
    pass

  def testAverageDist(self):
    assert (self.utils.average_distance(self.g) == 2300)

  def testBiggestPop(self):
    assert self.utils.biggest_city(self.g) == ('TYO', 34000000)

  def testSmallestPop(self):
    assert self.utils.smallest_city(self.g) == ( 'ESS', 589900)

  def testAveragePop(self):
    assert self.utils.average_city(self.g) == 11796143

  def testContinents(self):
    pass

  def testHubs(self):
    assert self.utils.get_hub_cities(self.g) == ['IST', 'HKG']