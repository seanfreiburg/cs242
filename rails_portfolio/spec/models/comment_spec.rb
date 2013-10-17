require 'spec_helper'

describe Comment do

  before(:each) do
    @project = Project.create(title: 'test project')
    FilteredWord.create(word: 'penis', replacement: 'peter')
  end

  describe "test comments" do
    it "make a normal comment" do
      comment = Comment.build_from(@project, 0, 'sadsadas')
      comment.save
      @project.root_comments.size.should be(1)
      @project.root_comments.first.body.should eq('sadsadas')

    end

    it "make a multiple root comments" do
      comment = Comment.build_from(@project, 0, 'sadsadas')
      comment.save
      comment = Comment.build_from(@project, 0, 'sadsjklkadas')
      comment.save
      @project.root_comments.size.should be(2)
      @project.root_comments.first.body.should eq('sadsadas')
      @project.root_comments.last.body.should eq('sadsjklkadas')

    end

    it "make a children comments" do
      comment1 = Comment.build_from(@project, 0, 'sadsadas')
      comment1.save
      comment2 = Comment.build_from(@project, 0, 'child')
      comment2.save
      comment3 = Comment.build_from(@project, 0, 'child2')
      comment3.save
      comment2.move_to_child_of(comment1)
      comment3.move_to_child_of(comment1)

      comment1.children.size.should be 2
      comment1.children.first.body.should eq('child')
      comment1.children.last.body.should eq('child2')

    end


    it "filters words" do
      comment = Comment.build_from(@project, 0, 'penis')
      comment.save
      @project.root_comments.size.should be(1)
      @project.root_comments.first.body.should eq('peter')

    end

  end


end
