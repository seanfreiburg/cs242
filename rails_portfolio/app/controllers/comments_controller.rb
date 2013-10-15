class CommentsController < ApplicationController

  def show
    @comment = Comment.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @comment }
    end
  end


  def new
    @comment = Comment.new


    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @comment }
    end
  end


  def create
    project = Project.find(params[:comment][:project_id])
    @comment = Comment.build_from(project,0,params[:comment][:body])

    if @comment.save
      begin
        parent = Comment.find(params[:comment][:parent_id])
      rescue => e
        parent = nil
      end
      unless parent.nil?
        @comment.move_to_child_of(parent)
      end

      redirect_to project_path(project)
    else
      render action: "new"
    end
  end


end
