class GamesController < ApplicationController

  def get_status
    @player = Player.find_by_key(params[:key])
    if @player.present?
      render json: @player
    else
      render json: "Invalid key"
    end
  end
end
