class Project < ActiveRecord::Base
  attr_accessible :date, :summary, :title, :version
  has_many :file_records

  def import_projects

  end
end
