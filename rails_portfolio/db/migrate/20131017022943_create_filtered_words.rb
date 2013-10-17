class CreateFilteredWords < ActiveRecord::Migration
  def change
    create_table :filtered_words do |t|
      t.string :word
      t.string :replacement

      t.timestamps
    end
  end
end
