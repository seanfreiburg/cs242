class FileRecord < ActiveRecord::Base
  attr_accessible :file, :name, :path, :project_id, :size, :type

  belongs_to :project
  has_many :file_versions
end
