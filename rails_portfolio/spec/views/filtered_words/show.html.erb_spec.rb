require 'spec_helper'

describe "filtered_words/show" do
  before(:each) do
    @filtered_word = assign(:filtered_word, stub_model(FilteredWord,
      :word => "Word",
      :replacement => "Replacement"
    ))
  end

  it "renders attributes in <p>" do
    render
    # Run the generator again with the --webrat flag if you want to use webrat matchers
    rendered.should match(/Word/)
    rendered.should match(/Replacement/)
  end
end
