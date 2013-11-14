require 'rspec'


class GameSpec



  describe 'Fold' do

    it 'should remove the players from active players' do
      pending

    end

    it 'should remove the players current bet' do
      pending
    end
  end

  describe 'Call' do

    it 'should do something' do
      pending

    end
  end

  describe 'Bet' do

    it 'should do something' do
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
      game.communityCards.length.should eq(3)
    end
  end

  describe "Turn" do
    it "should draw one card." do
      Game.instance.reset
      game = Game.instance
      game.turn
      game.communityCards.length.should eq(1)
    end
  end

  describe "River" do
    it "should draw one card." do
      Game.instance.reset
      game = Game.instance
      game.river
      game.communityCards.length.should eq(1)
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


end

