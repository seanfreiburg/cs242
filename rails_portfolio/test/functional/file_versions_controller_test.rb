require 'test_helper'

class FileVersionsControllerTest < ActionController::TestCase
  setup do
    @file_version = file_versions(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:file_versions)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create file_version" do
    assert_difference('FileVersion.count') do
      post :create, file_version: { author: @file_version.author, date: @file_version.date, file_record_id: @file_version.file_record_id, message: @file_version.message, revision: @file_version.revision }
    end

    assert_redirected_to file_version_path(assigns(:file_version))
  end

  test "should show file_version" do
    get :show, id: @file_version
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @file_version
    assert_response :success
  end

  test "should update file_version" do
    put :update, id: @file_version, file_version: { author: @file_version.author, date: @file_version.date, file_record_id: @file_version.file_record_id, message: @file_version.message, revision: @file_version.revision }
    assert_redirected_to file_version_path(assigns(:file_version))
  end

  test "should destroy file_version" do
    assert_difference('FileVersion.count', -1) do
      delete :destroy, id: @file_version
    end

    assert_redirected_to file_versions_path
  end
end
