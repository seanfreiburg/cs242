require 'spec_helper'

describe "filtered_words/index" do
  before(:each) do
    assign(:filtered_words, [
      stub_model(FilteredWord,
        :word => "Word",
        :replacement => "Replacement"
      ),
      stub_model(FilteredWord,
        :word => "Word",
        :replacement => "Replacement"
      )
    ])
  end

  it "renders a list of filtered_words" do
    render
    # Run the generator again with the --webrat flag if you want to use webrat matchers
    assert_select "tr>td", :text => "Word".to_s, :count => 2
    assert_select "tr>td", :text => "Replacement".to_s, :count => 2
  end
end
