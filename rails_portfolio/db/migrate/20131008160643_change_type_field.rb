class ChangeTypeField < ActiveRecord::Migration
  def change
    remove_column :file_records, :type
    add_column :file_records, :file_type, :string
  end
end
