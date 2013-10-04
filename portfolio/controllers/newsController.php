<?php


  class News_Controller extends Base_Controller
  {

    public $model = 'news';


    public function index(array $getVars)
    {

      $newsModel = new News_Model;

      //$article = $newsModel->get_article($getVars['author']);
      $article = array('title' => "blah", 'content' => 'blah');
      //create a new view and pass it our template
      $view = new View_Model( __FUNCTION__ ,$this->model);

      //assign article data to view
      $view->assign($article);
    }
  }