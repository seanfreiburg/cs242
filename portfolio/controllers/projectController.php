<?php


  class Project_Controller extends Base_Controller
  {

    public $model = 'project';
    public $projectModel;

    public function __construct()
    {
      $this->projectModel = new Project_Model;

    }

    public function index(array $getVars)
    {
      $projects = $this->projectModel->get_all_projects();
      $view = new View_Model($this->model, __FUNCTION__);
      $view->assign($projects);
    }

    public function show(array $getVars)
    {

      $project = $this->projectModel->get_project(1);
      $view = new View_Model($this->model, __FUNCTION__);
      $view->assign($project);
    }
  }