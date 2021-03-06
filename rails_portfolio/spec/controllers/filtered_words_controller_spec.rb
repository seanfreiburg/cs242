require 'spec_helper'

# This spec was generated by rspec-rails when you ran the scaffold generator.
# It demonstrates how one might use RSpec to specify the controller code that
# was generated by Rails when you ran the scaffold generator.
#
# It assumes that the implementation code is generated by the rails scaffold
# generator.  If you are using any extension libraries to generate different
# controller code, this generated spec may or may not pass.
#
# It only uses APIs available in rails and/or rspec-rails.  There are a number
# of tools you can use to make these specs even more expressive, but we're
# sticking to rails and rspec-rails APIs to keep things simple and stable.
#
# Compared to earlier versions of this generator, there is very limited use of
# stubs and message expectations in this spec.  Stubs are only used when there
# is no simpler way to get a handle on the object needed for the example.
# Message expectations are only used when there is no simpler way to specify
# that an instance is receiving a specific message.

describe FilteredWordsController do

  # This should return the minimal set of attributes required to create a valid
  # FilteredWord. As you add validations to FilteredWord, be sure to
  # update the return value of this method accordingly.
  def valid_attributes
    {}
  end

  # This should return the minimal set of values that should be in the session
  # in order to pass any filters (e.g. authentication) defined in
  # FilteredWordsController. Be sure to keep this updated too.
  def valid_session
    {}
  end

  describe "GET index" do
    it "assigns all filtered_words as @filtered_words" do
      filtered_word = FilteredWord.create! valid_attributes
      get :index, {}, valid_session
      assigns(:filtered_words).should eq([filtered_word])
    end
  end

  describe "GET show" do
    it "assigns the requested filtered_word as @filtered_word" do
      filtered_word = FilteredWord.create! valid_attributes
      get :show, {:id => filtered_word.to_param}, valid_session
      assigns(:filtered_word).should eq(filtered_word)
    end
  end

  describe "GET new" do
    it "assigns a new filtered_word as @filtered_word" do
      get :new, {}, valid_session
      assigns(:filtered_word).should be_a_new(FilteredWord)
    end
  end

  describe "GET edit" do
    it "assigns the requested filtered_word as @filtered_word" do
      filtered_word = FilteredWord.create! valid_attributes
      get :edit, {:id => filtered_word.to_param}, valid_session
      assigns(:filtered_word).should eq(filtered_word)
    end
  end

  describe "POST create" do
    describe "with valid params" do
      it "creates a new FilteredWord" do
        expect {
          post :create, {:filtered_word => valid_attributes}, valid_session
        }.to change(FilteredWord, :count).by(1)
      end

      it "assigns a newly created filtered_word as @filtered_word" do
        post :create, {:filtered_word => valid_attributes}, valid_session
        assigns(:filtered_word).should be_a(FilteredWord)
        assigns(:filtered_word).should be_persisted
      end

      it "redirects to the created filtered_word" do
        post :create, {:filtered_word => valid_attributes}, valid_session
        response.should redirect_to(FilteredWord.last)
      end
    end

    describe "with invalid params" do
      it "assigns a newly created but unsaved filtered_word as @filtered_word" do
        # Trigger the behavior that occurs when invalid params are submitted
        FilteredWord.any_instance.stub(:save).and_return(false)
        post :create, {:filtered_word => {}}, valid_session
        assigns(:filtered_word).should be_a_new(FilteredWord)
      end

      it "re-renders the 'new' template" do
        # Trigger the behavior that occurs when invalid params are submitted
        FilteredWord.any_instance.stub(:save).and_return(false)
        post :create, {:filtered_word => {}}, valid_session
        response.should render_template("new")
      end
    end
  end

  describe "PUT update" do
    describe "with valid params" do
      it "updates the requested filtered_word" do
        filtered_word = FilteredWord.create! valid_attributes
        # Assuming there are no other filtered_words in the database, this
        # specifies that the FilteredWord created on the previous line
        # receives the :update_attributes message with whatever params are
        # submitted in the request.
        FilteredWord.any_instance.should_receive(:update_attributes).with({'these' => 'params'})
        put :update, {:id => filtered_word.to_param, :filtered_word => {'these' => 'params'}}, valid_session
      end

      it "assigns the requested filtered_word as @filtered_word" do
        filtered_word = FilteredWord.create! valid_attributes
        put :update, {:id => filtered_word.to_param, :filtered_word => valid_attributes}, valid_session
        assigns(:filtered_word).should eq(filtered_word)
      end

      it "redirects to the filtered_word" do
        filtered_word = FilteredWord.create! valid_attributes
        put :update, {:id => filtered_word.to_param, :filtered_word => valid_attributes}, valid_session
        response.should redirect_to(filtered_word)
      end
    end

    describe "with invalid params" do
      it "assigns the filtered_word as @filtered_word" do
        filtered_word = FilteredWord.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FilteredWord.any_instance.stub(:save).and_return(false)
        put :update, {:id => filtered_word.to_param, :filtered_word => {}}, valid_session
        assigns(:filtered_word).should eq(filtered_word)
      end

      it "re-renders the 'edit' template" do
        filtered_word = FilteredWord.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FilteredWord.any_instance.stub(:save).and_return(false)
        put :update, {:id => filtered_word.to_param, :filtered_word => {}}, valid_session
        response.should render_template("edit")
      end
    end
  end

  describe "DELETE destroy" do
    it "destroys the requested filtered_word" do
      filtered_word = FilteredWord.create! valid_attributes
      expect {
        delete :destroy, {:id => filtered_word.to_param}, valid_session
      }.to change(FilteredWord, :count).by(-1)
    end

    it "redirects to the filtered_words list" do
      filtered_word = FilteredWord.create! valid_attributes
      delete :destroy, {:id => filtered_word.to_param}, valid_session
      response.should redirect_to(filtered_words_url)
    end
  end

end
