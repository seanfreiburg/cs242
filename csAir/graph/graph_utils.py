# coding=utf-8
import json
import sys

__author__ = 'seanfreiburg'


class GraphUtils:
  def __init__(self):
    self.longest_dist = None
    self.shortest_dist = None
    self.average_dist = None
    self.biggest_pop = None
    self.smallest_pop = None
    self.average_pop = None
    self.con_n_cities = None

  '''
  The longest single flight in the network
  @return a triple of start code, end code, and distance
  '''
  def longest_flight(self, graph):
    if (self.longest_dist is None):
      max_distance = 0
      for key, node in graph.nodes.iteritems():
        for entry in node.edges:
          distance = entry.distance
          if distance > max_distance:
            max_distance = distance
            start = node.code
            end = entry.destination

      self.longest_dist = (start, end, max_distance)

    return self.longest_dist
  '''
  the shortest single flight in the network
  @return a triple of start code, end code, and distance
  '''
  def shortest_flight(self, graph):
    if (self.shortest_dist is None):

      min_distance = sys.maxint
      for key, node in graph.nodes.iteritems():
        for entry in node.edges:
          distance = entry.distance
          if distance < min_distance:
            min_distance = distance
            start = node.code
            end = entry.destination

      self.shortest_dist = (start, end, min_distance)

    return self.shortest_dist

  # the average distance of all the flights in the network
  # @return the distince as a number
  def average_distance(self, graph):
    if (self.average_dist == None):
      number_of_flights = 0
      sum = 0
      for key, node in graph.nodes.iteritems():
        for entry in node.edges:
          number_of_flights += 1
          sum += entry.distance
          self.average_dist = sum / number_of_flights
    return self.average_dist

  # the biggest city (by population) served by CSAir
  # @return a tuple of the city code and population of the city
  def biggest_city(self, graph):
    if (self.biggest_pop == None):
      max_pop = 0
      city_code = ''
      for key, node in graph.nodes.iteritems():
        if (max_pop < node.population):
          max_pop = node.population
          city_code = node.code
      self.biggest_pop = (city_code, max_pop)
    return self.biggest_pop

  # the smallest city (by population) served by CSAir
  # @return a tuple of the city code and population of the city
  def smallest_city(self, graph):
    if (self.smallest_pop == None):
      min_pop = sys.maxint
      city_code = ''
      for key, node in graph.nodes.iteritems():
        if (min_pop > node.population):
          min_pop = node.population
          city_code = node.code
      self.smallest_pop = (city_code, min_pop)
    return self.smallest_pop

  # the average size (by population) of all the cities served by CSAir
  # @return a number of the average
  def average_city(self, graph):
    if (self.average_pop == None):
      number_of_cities = 0
      sum = 0
      for key, node in graph.nodes.iteritems():
        number_of_cities += 1
        sum += node.population
        self.average_pop = sum / number_of_cities
    return self.average_pop

  # a list of the continents served by CSAir and which cities are in them
  # @return a dict of continents as keys and city name in array as values
  def get_continents_and_cities(self, graph):
    continents_dict = dict()

    for key, node in graph.nodes.iteritems():
      city_arr = continents_dict.get(node.continent)
      if city_arr is None:
        continents_dict[node.continent] = [node.name]
      else:
        continents_dict[node.continent].append(node.name)
    return continents_dict

  # identifying CSAir's hub cities – the cities that have the most direct connections.
  # @return an array of city codes
  def get_hub_cities(self, graph):
    node_codes = []
    max_length = 0
    for key, node in graph.nodes.iteritems():
      if (len(node.edges) > max_length):
        node_codes = [node.code]
        max_length = len(node.edges)
      elif (len(node.edges) == max_length):
        node_codes.append(node.code)
    return node_codes


  # Gets the map string to add to the url
  # Should probably be abstracted
  # @return the map url sting to attach to the base url
  def get_map_string(self, graph):

    map_string = ''
    for key, node in graph.nodes.iteritems():
      for entry in node.edges:
        if (map_string == ''):
          map_string += node.code + '-' + entry.destination
        else:
          map_string += ',+' + node.code + '-' + entry.destination

    return map_string

  # Returns array of all city names and codes
  # @return array of all city names and codes
  def get_cities(self, graph):
    cities = []
    for key, node in graph.nodes.iteritems():
      cities.append((node.code, node.name))
    return cities

  def save_to_disk(self,graph):
    data = dict()
    data['metros'] = []
    for key,value in graph.nodes.iteritems():
      data['metros'].append({'code' : value.code, 'name' : value.name, 'country':value.country, 'continent': value.continent,
                             'timezone': value.timezone, 'coordinates': value.coordinates, 'population': value.population,
                             'region': value.region })
    data['routes'] = []
    for key,value in graph.nodes.iteritems():
      for edge in value.edges:
        data['routes'].append({'ports':[value.code, edge.destination] , 'distance': edge.distance})

    string_data = json.dumps(data)
    f = open('map_data_written', 'w')
    f.write(string_data)
    return

 #{
 #       "code": "SCL",
 #       "name": "Santiago",
 #       "country": "CL",
 #       "continent": "South America",
 #       "timezone": -4,
 #       "coordinates": {
 #           "S": 33,
 #           "W": 71
 #       },
 #       "population": 6000000,
 #       "region": 1
 #   },

