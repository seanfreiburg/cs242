class FileRecordsController < ApplicationController
  # GET /file_records
  # GET /file_records.json
  def index
    @file_records = FileRecord.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @file_records }
    end
  end

  # GET /file_records/1
  # GET /file_records/1.json
  def show
    @file_record = FileRecord.find(params[:id])
    @file_versions = @file_record.file_versions.all

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @file_record }
      format.json { render json: {file_record: @file_record,file_versions: @file_versions} }
    end
  end

  # GET /file_records/new
  # GET /file_records/new.json
  def new
    @file_record = FileRecord.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @file_record }
    end
  end

  # GET /file_records/1/edit
  def edit
    @file_record = FileRecord.find(params[:id])
  end

  # POST /file_records
  # POST /file_records.json
  def create
    @file_record = FileRecord.new(params[:file_record])

    respond_to do |format|
      if @file_record.save
        format.html { redirect_to @file_record, notice: 'File record was successfully created.' }
        format.json { render json: @file_record, status: :created, location: @file_record }
      else
        format.html { render action: "new" }
        format.json { render json: @file_record.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /file_records/1
  # PUT /file_records/1.json
  def update
    @file_record = FileRecord.find(params[:id])

    respond_to do |format|
      if @file_record.update_attributes(params[:file_record])
        format.html { redirect_to @file_record, notice: 'File record was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @file_record.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /file_records/1
  # DELETE /file_records/1.json
  def destroy
    @file_record = FileRecord.find(params[:id])
    @file_record.destroy

    respond_to do |format|
      format.html { redirect_to file_records_url }
      format.json { head :no_content }
    end
  end
end
