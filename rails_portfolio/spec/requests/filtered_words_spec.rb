require 'spec_helper'

describe "FilteredWords" do
  describe "GET /filtered_words" do
    it "works! (now write some real specs)" do
      # Run the generator again with the --webrat flag if you want to use webrat methods/matchers
      get filtered_words_path
      response.status.should be(200)
    end
  end
end
