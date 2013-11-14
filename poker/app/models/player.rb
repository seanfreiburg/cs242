require 'securerandom'

class Player < ActiveRecord::Base

  attr_accessor :money,:hand

  before_validation :create_key
  validates :name, presence: true, length: { in: 1..255 } , uniqueness: true
  validates :key,  presence: true, uniqueness: true

  def create_key
    self.key = SecureRandom.hex
  end

  def clearHand
    @hand = nil
  end

end

