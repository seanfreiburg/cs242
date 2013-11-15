require 'rspec'


class GameSpec



  describe 'Fold' do

    it 'should remove the players from active players' do
      player1 =Player.create(name: 'adssadad')
      player2 = Player.create(name: 'adssadad4')
      Game.instance.reset
      Game.instance.active_players = [player1,player2]
      Game.instance.fold(player1)
      Game.instance.active_players.size.should eq(1)

    end

    it 'should remove the players current bet' do
      player1 =Player.create(name: 'adssadad')
      player2 = Player.create(name: 'adssadad4')
      Game.instance.reset
      Game.instance.players = [player1,player2]
      Game.instance.fold(player1)
      Game.instance.current_bets[player1].should eq(nil)
    end
  end

  describe 'Call' do

    it 'should do something' do
      pending

    end
  end

  describe 'Bet' do

    it 'should return an error on a negative amount' do
      pending

    end

    it 'should return an error when you have less money than you bet' do
      pending

    end



    it 'should return an error when the bet is less than the big blind' do
      pending

    end


  end

  describe 'DetermineWinner' do

    it 'should do something' do
      pending

    end
  end

  describe 'DistributePot' do

    it 'should do something' do
      pending
    end
  end

  describe "Flop" do
    it "should draw three cards." do
      Game.instance.reset
      game = Game.instance
      game.flop
      game.community_cards.length.should eq(3)
    end
  end

  describe "Turn" do
    it "should draw one card." do
      Game.instance.reset
      game = Game.instance
      game.turn
      game.community_cards.length.should eq(1)
    end
  end

  describe "River" do
    it "should draw one card." do
      Game.instance.reset
      game = Game.instance
      game.river
      game.community_cards.length.should eq(1)
    end
  end

  describe 'Get Status' do
    it "should add a player if not yet added" do
      Player.create(name: 'adsad')
      Game.instance.reset
      Game.instance.players.size.should eq(0)
      Game.instance.open_tournament
      player = Player.first
      Game.instance.game_status(player)
      Game.instance.players.size.should eq(1)
    end

    it "should not add a player after already added" do
      Player.create(name: 'adssadad')
      Game.instance.reset
      Game.instance.open_tournament
      player = Player.first
      Game.instance.game_status(player)
      Game.instance.game_status(player)
      Game.instance.players.size.should eq(1)

    end

    it "should return a list of players if game is open" do
      Player.create(name: 'adssadad')
      Game.instance.reset
      Game.instance.open_tournament
      player = Player.first
      Game.instance.game_status(player)[:players].should eq( [player.name])

    end

    it "should return it is not active if tournament hasn't started" do
      Player.create(name: 'adssadad')
      Game.instance.reset
      Game.instance.open_tournament
      player = Player.first
      Game.instance.game_status(player)[:active].should eq(false)
    end





  end

  describe "Remove Broke Players" do
    it "should draw one card." do
      Game.instance.reset
      game = Game.instance
      player1 =Player.create(name: 'adssadad')
      player2 = Player.create(name: 'adssadad4')
      Game.instance.players = [player1,player2]
      Game.instance.stacks[player1] = 100
      Game.instance.stacks[player2] = 0
      Game.instance.remove_broke_players
      Game.instance.players.size.should eq(1)

    end
  end


end

