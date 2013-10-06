<?php

  class Project_Model extends Base_Model
  {

    private $db;

    public function __construct()
    {
      $this->db = new MysqlImproved_Driver;
    }


    public function get_project($id)
    {
      return array('title' => 'project' . $id, 'content' => 'stuff');
    }

    public function get_all_projects()
    {
      return array(array('title' => 'project 1', 'content' => 'stuff'), array('title' => 'project 2', 'content' => 'more stuff'));
    }

  }