class FilteredWordsController < ApplicationController
  # GET /filtered_words
  # GET /filtered_words.json
  def index
    @filtered_words = FilteredWord.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @filtered_words }
    end
  end

  # GET /filtered_words/1
  # GET /filtered_words/1.json
  def show
    @filtered_word = FilteredWord.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @filtered_word }
    end
  end

  # GET /filtered_words/new
  # GET /filtered_words/new.json
  def new
    @filtered_word = FilteredWord.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @filtered_word }
    end
  end

  # GET /filtered_words/1/edit
  def edit
    @filtered_word = FilteredWord.find(params[:id])
  end

  # POST /filtered_words
  # POST /filtered_words.json
  def create
    @filtered_word = FilteredWord.new(params[:filtered_word])

    respond_to do |format|
      if @filtered_word.save
        format.html { redirect_to @filtered_word, notice: 'Filtered word was successfully created.' }
        format.json { render json: @filtered_word, status: :created, location: @filtered_word }
      else
        format.html { render action: "new" }
        format.json { render json: @filtered_word.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /filtered_words/1
  # PUT /filtered_words/1.json
  def update
    @filtered_word = FilteredWord.find(params[:id])

    respond_to do |format|
      if @filtered_word.update_attributes(params[:filtered_word])
        format.html { redirect_to @filtered_word, notice: 'Filtered word was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @filtered_word.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /filtered_words/1
  # DELETE /filtered_words/1.json
  def destroy
    @filtered_word = FilteredWord.find(params[:id])
    @filtered_word.destroy

    respond_to do |format|
      format.html { redirect_to filtered_words_url }
      format.json { head :no_content }
    end
  end
end
