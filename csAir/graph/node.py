__author__ = 'seanfreiburg'
from edge import *

class Node:
  def __init__(self, code, name, country, continent, timezone, latitude, longitude, population, region):
    self.code = code
    self.name = name
    self.name = name
    self.country = country
    self.continent = continent
    self.timezone = timezone
    # @todo find out how to do longitude and lat
    #self.latitude = latitude
    #self.longitude = longitude
    self.population = population
    self.region = region
    self.edges = []



  def add_edge(self,code, distance):
    self.edges.append(Edge(code, distance))

  def get_destinations(self):
    destinations = []
    for edge in self.edges:
      destinations.append(edge.destination)
    return destinations


