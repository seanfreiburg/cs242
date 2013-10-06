class CreateFileRecords < ActiveRecord::Migration
  def change
    create_table :file_records do |t|
      t.string :name
      t.integer :size
      t.integer :project_id
      t.string :type
      t.text :path
      t.binary :file

      t.timestamps
    end
  end
end
