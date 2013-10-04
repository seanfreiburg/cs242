<?php

  class View_Model
  {

    private $data = array();

    private $render = FALSE;


    public function __construct($action,$model)
    {
      //compose file name
      $file = SERVER_ROOT . '/views/' . strtolower($model). '/'. $action . '.php';

      if (file_exists($file))
      {
        $this->render = $file;
      }
    }

    /**
     * Receives assignments from controller and stores in local data array
     *
     * @param $variable
     * @param $value
     */
    public function assign($data)
    {
      $this->data = $data;
    }

    public function __destruct()
    {
      //parse data variables into local variables, so that they render to the view
      $data = $this->data;

      //render view
      include($this->render);
    }
  }