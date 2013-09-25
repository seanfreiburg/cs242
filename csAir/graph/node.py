__author__ = 'seanfreiburg'
from edge import *

# Holds the city info
class Node:
  # Assigns values as constructor
  def __init__(self, code, name, country, continent, timezone, coordinates, population, region):
    self.code = code
    self.name = name
    self.name = name
    self.country = country
    self.continent = continent
    self.timezone = timezone
    self.coordinates = coordinates
    self.population = population
    self.region = region
    self.edges = []

  #Adds edge to the edges array
  def add_edge(self, code, distance):
    self.edges.append(Edge(code, distance))

  # Return a array of destinations from edges array
  def get_destinations(self):
    destinations = []
    for edge in self.edges:
      destinations.append(edge.destination)
    return destinations



