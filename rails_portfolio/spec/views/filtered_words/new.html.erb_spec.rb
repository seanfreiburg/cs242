require 'spec_helper'

describe "filtered_words/new" do
  before(:each) do
    assign(:filtered_word, stub_model(FilteredWord,
      :word => "MyString",
      :replacement => "MyString"
    ).as_new_record)
  end

  it "renders new filtered_word form" do
    render

    # Run the generator again with the --webrat flag if you want to use webrat matchers
    assert_select "form", :action => filtered_words_path, :method => "post" do
      assert_select "input#filtered_word_word", :name => "filtered_word[word]"
      assert_select "input#filtered_word_replacement", :name => "filtered_word[replacement]"
    end
  end
end
