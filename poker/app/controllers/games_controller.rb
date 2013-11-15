class GamesController < ApplicationController

  def get_status
    @player = Player.find_by_key(params[:key])
    if @player.present?
      render json: Game.instance.game_status(@player)
    else
      render json: "Invalid key"
    end
  end


  def send_action_and_amount

    render json: Game.instance.set_action(params[:move],params[:amount].to_i)
  end


  def open_tournament
    if params[:key] == Game.instance.MASTER_API_KEY

      render json: Game.instance.open_tournament
    else
      render json: "Invalid key, key is #{Game.instance.MASTER_API_KEY} and you said #{params[:key]}"
    end
  end


  def start_tournament
    if params[:key] == Game.instance.MASTER_API_KEY
      Thread.new do
        Game.instance.start_tournament
      end

      render json: 'Tournament has started'
    else
      render json: "Invalid key"
    end
  end

end
