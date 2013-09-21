__author__ = 'seanfreiburg'
from graph import graph
import pprint

import json

f = open('assets/data/map_data.json', 'r')
decoded = json.loads(f.read())
g = graph.Graph(decoded['metros'], decoded['routes'])


print("Enter a country code for data about it: ")
code = raw_input()
print(g.get_node(code).name)


