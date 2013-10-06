class FileVersion < ActiveRecord::Base
  attr_accessible :author, :date, :file_record_id, :message, :revision
  belongs_to :file_record
end
