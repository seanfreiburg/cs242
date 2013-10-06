class CreateProjects < ActiveRecord::Migration
  def change
    create_table :projects do |t|
      t.string :title
      t.datetime :date
      t.string :version
      t.text :summary

      t.timestamps
    end
  end
end
