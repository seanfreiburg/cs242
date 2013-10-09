require 'spec_helper'

describe FileVersionsController do

  # This should return the minimal set of attributes required to create a valid
  # FileVersion. As you add validations to FileVersion, be sure to
  # update the return value of this method accordingly.
  def valid_attributes
    { }
  end

  # This should return the minimal set of values that should be in the session
  # in order to pass any filters (e.g. authentication) defined in
  # FileVersionsController. Be sure to keep this updated too.
  def valid_session
    {}
  end

  describe "GET index" do
    it "assigns all file_versions as @file_versions" do
      file_version = FileVersion.create! valid_attributes
      get :index, {}, valid_session
      assigns(:file_versions).should eq([file_version])
    end
  end

  describe "GET show" do
    it "assigns the requested file_version as @file_version" do
      file_version = FileVersion.create! valid_attributes
      get :show, {:id => file_version.to_param}, valid_session
      assigns(:file_version).should eq(file_version)
    end
  end

  describe "GET new" do
    it "assigns a new file_version as @file_version" do
      get :new, {}, valid_session
      assigns(:file_version).should be_a_new(FileVersion)
    end
  end

  describe "GET edit" do
    it "assigns the requested file_version as @file_version" do
      file_version = FileVersion.create! valid_attributes
      get :edit, {:id => file_version.to_param}, valid_session
      assigns(:file_version).should eq(file_version)
    end
  end

  describe "POST create" do
    describe "with valid params" do
      it "creates a new FileVersion" do
        expect {
          post :create, {:file_version => valid_attributes}, valid_session
        }.to change(FileVersion, :count).by(1)
      end

      it "assigns a newly created file_version as @file_version" do
        post :create, {:file_version => valid_attributes}, valid_session
        assigns(:file_version).should be_a(FileVersion)
        assigns(:file_version).should be_persisted
      end

      it "redirects to the created file_version" do
        post :create, {:file_version => valid_attributes}, valid_session
        response.should redirect_to(FileVersion.last)
      end
    end

    describe "with invalid params" do
      it "assigns a newly created but unsaved file_version as @file_version" do
        # Trigger the behavior that occurs when invalid params are submitted
        FileVersion.any_instance.stub(:save).and_return(false)
        post :create, {:file_version => {}}, valid_session
        assigns(:file_version).should be_a_new(FileVersion)
      end

      it "re-renders the 'new' template" do
        # Trigger the behavior that occurs when invalid params are submitted
        FileVersion.any_instance.stub(:save).and_return(false)
        post :create, {:file_version => {}}, valid_session
        response.should render_template("new")
      end
    end
  end

  describe "PUT update" do
    describe "with valid params" do
      it "updates the requested file_version" do
        file_version = FileVersion.create! valid_attributes
        # Assuming there are no other file_versions in the database, this
        # specifies that the FileVersion created on the previous line
        # receives the :update_attributes message with whatever params are
        # submitted in the request.
        FileVersion.any_instance.should_receive(:update_attributes).with({'these' => 'params'})
        put :update, {:id => file_version.to_param, :file_version => {'these' => 'params'}}, valid_session
      end

      it "assigns the requested file_version as @file_version" do
        file_version = FileVersion.create! valid_attributes
        put :update, {:id => file_version.to_param, :file_version => valid_attributes}, valid_session
        assigns(:file_version).should eq(file_version)
      end

      it "redirects to the file_version" do
        file_version = FileVersion.create! valid_attributes
        put :update, {:id => file_version.to_param, :file_version => valid_attributes}, valid_session
        response.should redirect_to(file_version)
      end
    end

    describe "with invalid params" do
      it "assigns the file_version as @file_version" do
        file_version = FileVersion.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FileVersion.any_instance.stub(:save).and_return(false)
        put :update, {:id => file_version.to_param, :file_version => {}}, valid_session
        assigns(:file_version).should eq(file_version)
      end

      it "re-renders the 'edit' template" do
        file_version = FileVersion.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        FileVersion.any_instance.stub(:save).and_return(false)
        put :update, {:id => file_version.to_param, :file_version => {}}, valid_session
        response.should render_template("edit")
      end
    end
  end

  describe "DELETE destroy" do
    it "destroys the requested file_version" do
      file_version = FileVersion.create! valid_attributes
      expect {
        delete :destroy, {:id => file_version.to_param}, valid_session
      }.to change(FileVersion, :count).by(-1)
    end

    it "redirects to the file_versions list" do
      file_version = FileVersion.create! valid_attributes
      delete :destroy, {:id => file_version.to_param}, valid_session
      response.should redirect_to(file_versions_url)
    end
  end

end
