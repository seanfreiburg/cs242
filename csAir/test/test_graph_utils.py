from unittest import TestCase
from graph.graph_utils import GraphUtils

__author__ = 'seanfreiburg'
from graph import graph
import json


class TestGraphUtils(TestCase):
  def setUp(self):
    f = open('assets/data/map_data.json', 'r')
    decoded = json.loads(f.read())
    self.g = graph.Graph()
    self.g.build_nodes(decoded['metros'])
    self.g.build_edges(decoded['routes'])
    self.utils = GraphUtils()

    f = open('assets/data/test1_map_data.json', 'r')
    decoded = json.loads(f.read())
    self.gSmall = graph.Graph()
    self.gSmall.build_nodes(decoded['metros'])
    self.gSmall.build_edges(decoded['routes'])

  def testSave(self):
    self.utils.save_to_disk(self.g)
    f2 = open('assets/data/map_data_written.json', 'r')
    decoded = json.loads(f2.read())
    self.g2 = graph.Graph()
    self.g2.build_nodes(decoded['metros'])
    self.g2.build_edges(decoded['routes'])
    self.utils = GraphUtils()
    assert (self.utils.longest_flight(self.g2) == ('SYD', 'LAX', 12051))
    assert (self.utils.shortest_flight(self.g2) == ('NYC', 'WAS', 334))
    assert (self.utils.average_distance(self.g2) == 2300)
    assert self.utils.biggest_city(self.g2) == ('TYO', 34000000)
    assert self.utils.smallest_city(self.g2) == ( 'ESS', 589900)


  def testLongestFlight(self):
    assert (self.utils.longest_flight(self.g) == ('SYD', 'LAX', 12051))
    assert (self.utils.longest_flight(self.gSmall) == ('MEX', 'LIM', 24530))

  def testShortestFlight(self):
    assert (self.utils.shortest_flight(self.g) == ('NYC', 'WAS', 334))
    assert (self.utils.shortest_flight(self.gSmall) == ('SCL', 'LIM', 2453))

  def testMapUrl(self):
    assert (self.utils.get_map_string(
      self.g) == 'PAR-ALG,+PAR-MAD,+PAR-LON,+PAR-ESS,+PAR-MIL,+MIL-PAR,+MIL-ESS,+MIL-IST,+MIA-MEX,+MIA-BOG,+MIA-ATL,+MIA-WAS,+LIM-SCL,+LIM-MEX,+LIM-BOG,+ATL-CHI,+ATL-WAS,+ATL-MIA,+PEK-SHA,+PEK-ICN,+LON-MAD,+LON-NYC,+LON-ESS,+LON-PAR,+IST-CAI,+IST-ALG,+IST-MIL,+IST-LED,+IST-MOW,+IST-BGW,+LOS-SAO,+LOS-KRT,+LOS-FIH,+CAI-KRT,+CAI-ALG,+CAI-IST,+CAI-BGW,+CAI-RUH,+DEL-THR,+DEL-KHI,+DEL-CCU,+DEL-MAA,+DEL-BOM,+BOM-KHI,+BOM-DEL,+BOM-MAA,+BGW-CAI,+BGW-IST,+BGW-THR,+BGW-KHI,+BGW-RUH,+NYC-MAD,+NYC-LON,+NYC-WAS,+NYC-YYZ,+BOG-LIM,+BOG-MEX,+BOG-MIA,+BOG-SAO,+BOG-BUE,+SCL-LIM,+SAO-BOG,+SAO-BUE,+SAO-MAD,+SAO-LOS,+KHI-BGW,+KHI-THR,+KHI-RUH,+KHI-DEL,+KHI-BOM,+JKT-MAA,+JKT-BKK,+JKT-SGN,+JKT-SYD,+BKK-MAA,+BKK-CCU,+BKK-HKG,+BKK-SGN,+BKK-JKT,+CCU-DEL,+CCU-MAA,+CCU-HKG,+CCU-BKK,+MNL-HKG,+MNL-TPE,+MNL-SFO,+MNL-SYD,+MNL-SGN,+SGN-BKK,+SGN-HKG,+SGN-MNL,+SGN-JKT,+OSA-TYO,+OSA-TPE,+HKG-CCU,+HKG-BKK,+HKG-SHA,+HKG-TPE,+HKG-MNL,+HKG-SGN,+BUE-BOG,+BUE-SAO,+TPE-HKG,+TPE-SHA,+TPE-OSA,+TPE-MNL,+ESS-LON,+ESS-PAR,+ESS-MIL,+ESS-LED,+ICN-SHA,+ICN-PEK,+ICN-TYO,+JNB-FIH,+JNB-KRT,+CHI-MEX,+CHI-LAX,+CHI-SFO,+CHI-YYZ,+CHI-ATL,+THR-MOW,+THR-BGW,+THR-DEL,+THR-KHI,+THR-RUH,+KRT-LOS,+KRT-FIH,+KRT-JNB,+KRT-CAI,+SHA-HKG,+SHA-PEK,+SHA-ICN,+SHA-TYO,+SHA-TPE,+FIH-LOS,+FIH-KRT,+FIH-JNB,+WAS-ATL,+WAS-MIA,+WAS-YYZ,+WAS-NYC,+RUH-CAI,+RUH-BGW,+RUH-THR,+RUH-KHI,+TYO-SHA,+TYO-ICN,+TYO-SFO,+TYO-OSA,+LED-ESS,+LED-MOW,+LED-IST,+SYD-MNL,+SYD-JKT,+SYD-LAX,+ALG-CAI,+ALG-MAD,+ALG-PAR,+ALG-IST,+MOW-LED,+MOW-THR,+MOW-IST,+YYZ-CHI,+YYZ-WAS,+YYZ-NYC,+MAA-DEL,+MAA-BOM,+MAA-CCU,+MAA-BKK,+MAA-JKT,+SFO-TYO,+SFO-MNL,+SFO-LAX,+SFO-CHI,+LAX-MEX,+LAX-SYD,+LAX-SFO,+LAX-CHI,+MAD-SAO,+MAD-ALG,+MAD-NYC,+MAD-LON,+MAD-PAR,+MEX-LIM,+MEX-LAX,+MEX-CHI,+MEX-MIA,+MEX-BOG')

  def testAverageDist(self):
    assert (self.utils.average_distance(self.g) == 2300)

  def testBiggestPop(self):
    assert self.utils.biggest_city(self.g) == ('TYO', 34000000)

  def testSmallestPop(self):
    assert self.utils.smallest_city(self.g) == ( 'ESS', 589900)

  def testAveragePop(self):
    assert self.utils.average_city(self.g) == 11796143

  def testContinents(self):
    assert self.utils.get_continents_and_cities(self.g) == {
    u'Europe': [u'Paris', u'Milan', u'London', u'Istanbul', u'Essen', u'St. Petersburg', u'Moscow', u'Madrid'],
    u'Australia': [u'Sydney'], u'Africa': [u'Lagos', u'Cairo', u'Johannesburg', u'Khartoum', u'Kinshasa', u'Algiers'],
    u'Asia': [u'Beijing', u'Delhi', u'Mumbai', u'Bagdad', u'Karachi', u'Jakarta', u'Bangkok', u'Calcutta', u'Manila',
              u'Ho Chi Minh City', u'Osaka', u'Hong Kong', u'Taipei', u'Seoul', u'Tehrah', u'Shanghai', u'Riyadh',
              u'Tokyo', u'Chennai'],
    u'North America': [u'Miami', u'Atlanta', u'New York', u'Chicago', u'Washington', u'Toronto', u'San Francisco',
                       u'Los Angeles', u'Mexico City'],
    u'South America': [u'Lima', u'Bogota', u'Santiago', u'Sao Paulo', u'Buenos Aires']}

  def testHubs(self):
    assert self.utils.get_hub_cities(self.g) == ['IST', 'HKG']

  def testShortestPath(self):
    print(self.utils.shortestPath(self.g, 'CHI', 'TYO'))
    assert self.utils.shortestPath(self.g, 'CHI', 'TYO') == ['CHI', 'SFO', 'TYO']

  def testRouteDistance(self):
    assert sum(self.utils.route_distance(self.g, ['CHI', 'SFO', 'TYO'])) == (8296 + 2994)

  def testShortestPath(self):
    assert (self.utils.shortestPath(self.g, 'CHI', 'TYO') == ['CHI', 'SFO', 'TYO'])

