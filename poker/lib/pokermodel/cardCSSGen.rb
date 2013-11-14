	string = ""
	x = -1
	for card in ["ace","two","three","four","five","six","seven","eight","nine","ten","jack","queen","king"]
		y = -1
		for suit in ["clubs","spades","hearts","diamonds"]
			string += "\n"
			string += "."+card+"_"+suit+"{\n"
			string += "\twidth:71px;\n"
			string += "\theight:96px;\n"
			string += "\tbackground:url(../images/playingcards.png) "+x.to_s+" "+y.to_s+";\n"
			string += "}\n"
			y += -98
		end
		x += -73
	end
	File.write("cards.css",string)