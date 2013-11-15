require 'securerandom'

class Player < ActiveRecord::Base

  attr_reader :name

  before_validation :create_key
  validates :name, presence: true, length: { in: 1..255 } , uniqueness: true
  validates :key,  presence: true, uniqueness: true

  def create_key
    self.key = SecureRandom.hex if self.key.nil?
  end



end

