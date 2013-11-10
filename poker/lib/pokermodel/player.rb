class Player
  attr_accessor :money
  attr_reader :id


  def initialize(id,money)
    @id = id
    @money = money
  end
end