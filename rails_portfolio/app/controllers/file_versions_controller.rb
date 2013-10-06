class FileVersionsController < ApplicationController
  # GET /file_versions
  # GET /file_versions.json
  def index
    @file_versions = FileVersion.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @file_versions }
    end
  end

  # GET /file_versions/1
  # GET /file_versions/1.json
  def show
    @file_version = FileVersion.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @file_version }
    end
  end

  # GET /file_versions/new
  # GET /file_versions/new.json
  def new
    @file_version = FileVersion.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @file_version }
    end
  end

  # GET /file_versions/1/edit
  def edit
    @file_version = FileVersion.find(params[:id])
  end

  # POST /file_versions
  # POST /file_versions.json
  def create
    @file_version = FileVersion.new(params[:file_version])

    respond_to do |format|
      if @file_version.save
        format.html { redirect_to @file_version, notice: 'File version was successfully created.' }
        format.json { render json: @file_version, status: :created, location: @file_version }
      else
        format.html { render action: "new" }
        format.json { render json: @file_version.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /file_versions/1
  # PUT /file_versions/1.json
  def update
    @file_version = FileVersion.find(params[:id])

    respond_to do |format|
      if @file_version.update_attributes(params[:file_version])
        format.html { redirect_to @file_version, notice: 'File version was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @file_version.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /file_versions/1
  # DELETE /file_versions/1.json
  def destroy
    @file_version = FileVersion.find(params[:id])
    @file_version.destroy

    respond_to do |format|
      format.html { redirect_to file_versions_url }
      format.json { head :no_content }
    end
  end
end
