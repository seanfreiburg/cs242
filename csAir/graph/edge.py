__author__ = 'seanfreiburg'

# Edge class
# Simply contains data needed to be stored on edges
class Edge:
  def __init__(self, destination, distance):
    self.destination = destination
    self.distance = distance