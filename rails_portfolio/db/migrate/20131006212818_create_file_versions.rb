class CreateFileVersions < ActiveRecord::Migration
  def change
    create_table :file_versions do |t|
      t.integer :revision
      t.integer :file_record_id
      t.string :author
      t.text :message
      t.datetime :date

      t.timestamps
    end
  end
end
