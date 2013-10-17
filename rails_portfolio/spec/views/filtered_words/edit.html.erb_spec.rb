require 'spec_helper'

describe "filtered_words/edit" do
  before(:each) do
    @filtered_word = assign(:filtered_word, stub_model(FilteredWord,
      :word => "MyString",
      :replacement => "MyString"
    ))
  end

  it "renders the edit filtered_word form" do
    render

    # Run the generator again with the --webrat flag if you want to use webrat matchers
    assert_select "form", :action => filtered_words_path(@filtered_word), :method => "post" do
      assert_select "input#filtered_word_word", :name => "filtered_word[word]"
      assert_select "input#filtered_word_replacement", :name => "filtered_word[replacement]"
    end
  end
end
