<?php

  class View_Model
  {

    private $data = array();

    private $render = FALSE;


    public function __construct($model,$action)
    {
      //compose file name
      $file = SERVER_ROOT . '/views/' . strtolower($model). '/'. $action . '.php';

      if (file_exists($file))
      {
        $this->render = $file;
      }
    }


    public function assign($variable , $value)
    {
      $this->data[$variable] = $value;
    }

    public function render($direct_output = TRUE)
    {
      // Turn output buffering on, capturing all output
      if ($direct_output !== TRUE)
      {
        ob_start();
      }

      // Parse data variables into local variables
      $data = $this->data;

      // Get template
      include($this->render);

      // Get the contents of the buffer and return it
      if ($direct_output !== TRUE)
      {
        return ob_get_clean();
      }
    }

    public function __destruct()
    {
      $this->render();
    }
  }