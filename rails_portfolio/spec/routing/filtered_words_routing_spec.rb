require "spec_helper"

describe FilteredWordsController do
  describe "routing" do

    it "routes to #index" do
      get("/filtered_words").should route_to("filtered_words#index")
    end

    it "routes to #new" do
      get("/filtered_words/new").should route_to("filtered_words#new")
    end

    it "routes to #show" do
      get("/filtered_words/1").should route_to("filtered_words#show", :id => "1")
    end

    it "routes to #edit" do
      get("/filtered_words/1/edit").should route_to("filtered_words#edit", :id => "1")
    end

    it "routes to #create" do
      post("/filtered_words").should route_to("filtered_words#create")
    end

    it "routes to #update" do
      put("/filtered_words/1").should route_to("filtered_words#update", :id => "1")
    end

    it "routes to #destroy" do
      delete("/filtered_words/1").should route_to("filtered_words#destroy", :id => "1")
    end

  end
end
