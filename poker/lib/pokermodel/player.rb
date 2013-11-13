class Player
  attr_accessor :money,:hand
  attr_reader :id


  def initialize(id,money)
    @id = id
    @money = money
    @hand = Hash.new
  end

  def clearHand
    @hand = Hash.new
  end
end