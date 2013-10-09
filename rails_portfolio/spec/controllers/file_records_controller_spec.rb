require 'spec_helper'

describe FileRecordsController do

  # This should return the minimal set of attributes required to create a valid
  # FileRecord. As you add validations to FileRecord, be sure to
  # update the return value of this method accordingly.
  def valid_attributes
    { }
  end

  # This should return the minimal set of values that should be in the session
  # in order to pass any filters (e.g. authentication) defined in
  # FileRecordsController. Be sure to keep this updated too.
  def valid_session
    {}
  end

  describe "GET index" do
    it "assigns all file_records as @file_records" do
      file_record = FileRecord.create! valid_attributes
      get :index, {}, valid_session
      assigns(:file_records).should eq([file_record])
    end
  end

  describe "GET show" do
    it "assigns the requested file_record as @file_record" do
      file_record = FileRecord.create! valid_attributes
      get :show, {:id => file_record.to_param}, valid_session
      assigns(:file_record).should eq(file_record)
    end
  end

  describe "GET new" do
    it "assigns a new file_record as @file_record" do
      get :new, {}, valid_session
      assigns(:file_record).should be_a_new(FileRecord)
    end
  end

  describe "GET edit" do
    it "assigns the requested file_record as @file_record" do
      file_record = FileRecord.create! valid_attributes
      get :edit, {:id => file_record.to_param}, valid_session
      assigns(:file_record).should eq(file_record)
    end
  end

  describe "POST create" do
    describe "with valid params" do
      it "creates a new FileRecord" do
        expect {
          post :create, {:file_record => valid_attributes}, valid_session
        }.to change(FileRecord, :count).by(1)
      end

      it "assigns a newly created file_record as @file_record" do
        post :create, {:file_record => valid_attributes}, valid_session
        assigns(:file_record).should be_a(FileRecord)
        assigns(:file_record).should be_persisted
      end

      it "redirects to the created file_record" do
        post :create, {:file_record => valid_attributes}, valid_session
        response.should redirect_to(FileRecord.last)
      end
    end

    describe "with invalid params" do
      it "assigns a newly created but unsaved file_record as @file_record" do
        # Trigger the behavior that occurs when invalid params are submitted
        FileRecord.any_instance.stub(:save).and_return(false)
        post :create, {:file_record => {}}, valid_session
        assigns(:file_record).should be_a_new(FileRecord)
      end

      it "re-renders the 'new' template" do
        # Trigger the behavior that occurs when invalid params are submitted
        FileRecord.any_instance.stub(:save).and_return(false)
        post :create, {:file_record => {}}, valid_session
        response.should render_template("new")
      end
    end
  end

  describe "PUT update" do
    describe "with valid params" do
      it "updates the requested file_record" do
        file_record = FileRecord.create! valid_attributes
        # Assuming there are no other file_records in the database, this
        # specifies that the FileRecord created on the previous line
        # receives the :update_attributes message with whatever params are
        # submitted in the request.
        FileRecord.any_instance.should_receive(:update_attributes).with({'these' => 'params'})
        put :update, {:id => file_record.to_param, :file_record => {'these' => 'params'}}, valid_session
      end

      it "assigns the requested file_record as @file_record" do
        file_record = FileRecord.create! valid_attributes
        put :update, {:id => file_record.to_param, :file_record => valid_attributes}, valid_session
        assigns(:file_record).should eq(file_record)
      end

      it "redirects to the file_record" do
        file_record = FileRecord.create! valid_attributes
        put :update, {:id => file_record.to_param, :file_record => valid_attributes}, valid_session
        response.should redirect_to(file_record)
      end
    end

    describe "with invalid params" do
      it "assigns the file_record as @file_record" do
        file_record = FileRecord.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FileRecord.any_instance.stub(:save).and_return(false)
        put :update, {:id => file_record.to_param, :file_record => {}}, valid_session
        assigns(:file_record).should eq(file_record)
      end

      it "re-renders the 'edit' template" do
        file_record = FileRecord.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FileRecord.any_instance.stub(:save).and_return(false)
        put :update, {:id => file_record.to_param, :file_record => {}}, valid_session
        response.should render_template("edit")
      end
    end
  end

  describe "DELETE destroy" do
    it "destroys the requested file_record" do
      file_record = FileRecord.create! valid_attributes
      expect {
        delete :destroy, {:id => file_record.to_param}, valid_session
      }.to change(FileRecord, :count).by(-1)
    end

    it "redirects to the file_records list" do
      file_record = FileRecord.create! valid_attributes
      delete :destroy, {:id => file_record.to_param}, valid_session
      response.should redirect_to(file_records_url)
    end
  end

end
