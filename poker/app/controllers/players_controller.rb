class PlayersController < ApplicationController


  def new
    @player = Player.new
  end

  def create_api
    @player = Player.new params.permit(:name)
    if @player.save
      render json: {name: @player.name, key: @player.key}
    else
      render json: {error: 'Name already taken'}
    end
  end

  def create
    @player = Player.new params.require(:player).permit(:name)
    if @player.save
      redirect_to player_path(:key => @player.key)
    else
      flash[:error] = @player.errors.full_messages
      render 'new'
    end
  end

  def show
    @player = Player.find_by_key(params[:key])
    if @player.nil?
      flash[:error] = "You don't have the correct key"
      redirect_to root_url
    end
  end


end
