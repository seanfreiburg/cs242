require 'spec_helper'

describe Project do

  before(:each) do
    load "#{Rails.root}/db/seeds.rb"
  end

  describe "test data import" do
    it "gets gitignore project" do
      project = Project.find_by_title('.gitignore')
      project.title.should eq('.gitignore')
    end

    it "gets Assignment1 project" do
      project = Project.find_by_title('Assignment1')
      project.title.should eq('Assignment1')
    end

    it "gets HW0 project" do
      project = Project.find_by_title('HW0')
      project.title.should eq('HW0')
    end

    it "gets csAir project" do
      project = Project.find_by_title('csAir')
      project.title.should eq('csAir')
    end

    it "gets rails_portfolio project" do
      project = Project.find_by_title('rails_portfolio')
      project.title.should eq('rails_portfolio')
    end
  end


end
