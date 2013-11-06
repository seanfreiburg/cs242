class PlayersController < ApplicationController

  def new
    @player = Player.new
  end

  def create
    @player = Player.new params.require(:player).permit(:name)
    if @player.save
      redirect_to @player
    else
      flash[:error] = @player.errors.full_messages
      render 'new'
    end
  end

  def show
    @player = Player.find params[:id]
  end
end
