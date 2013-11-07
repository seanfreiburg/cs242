require 'spec_helper'


describe PlayersController do

  def valid_attributes
    {:name => SecureRandom.hex}
  end


  def valid_session
    {}
  end


  describe "GET show" do
    it "assigns the requested player as @player" do
      player = Player.create! valid_attributes
      get :show, {:key => player.key}, valid_session
      assigns(:player).should eq(player)
    end
  end

  describe "GET new" do
    it "assigns a new player as @player" do
      get :new, {}, valid_session
      assigns(:player).should be_a_new(Player)
    end
  end


  describe "POST create" do
    describe "with valid params" do
      it "creates a new Player" do
        expect {
          post :create, {:player => valid_attributes}, valid_session
        }.to change(Player, :count).by(1)
      end

      it "assigns a newly created player as @player" do
        post :create, {:player => valid_attributes}, valid_session
        assigns(:player).should be_a(Player)
        assigns(:player).should be_persisted
      end

    end

  end


end
