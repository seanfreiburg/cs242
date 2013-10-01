import sys

__author__ = 'seanfreiburg'
from edge import *

# Holds the city info
class Node:
  # Assigns values as constructor
  def __init__(self, metro):
    self.code = metro['code']
    self.name = metro['name']
    self.country = metro['country']
    self.continent = metro['continent']
    self.timezone = metro['timezone']
    self.coordinates = metro['coordinates']
    self.population = metro['population']
    self.region = metro['region']
    self.edges = []

  # Adds edge to the edges array
  # @param code - The city code in a string
  # @param distance - The distance of the flight in an int
  # @return void
  def add_edge(self, code, distance):
    self.edges.append(Edge(code, distance))

  # Return a array of destinations from edges array
  # @return array of city code
  def get_destinations(self):
    destinations = []
    for edge in self.edges:
      destinations.append(edge.destination)
    return destinations

  def edit(self,data):
    self.code = data['code']
    self.name = data['name']
    self.country = data['country']
    self.continent = data['continent']
    self.timezone = data['timezone']
    self.coordinates = data['coordinates']
    self.population = data['population']
    self.region = data['region']



