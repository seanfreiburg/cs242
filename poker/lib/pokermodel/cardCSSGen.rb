	string = ""
	x = -1
	for card in ["A","2","3","4","5","6","7","8","9","T","J","Q","K"]
		y = -1
		for suit in ["C","S","H","D"]
			string += "\n"
			string += "."+card+suit+"{\n"
			string += "\twidth:71px;\n"
			string += "\theight:96px;\n"
			string += "\tbackground:url(../images/playingcards.png) "+x.to_s+"px "+y.to_s+"px ;\n"
			string += "}\n"
			y += -98
		end
		x += -73
	end
	File.write("./assets/stylesheets/cards.css",string)