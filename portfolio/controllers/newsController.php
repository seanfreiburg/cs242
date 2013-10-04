<?php


  class News_Controller extends Base_Controller
  {

    public $model = 'news';
    private $newsModel;

    public function __construct(){
      $newsModel = new News_Model;

    }

    public function index(array $getVars)
    {
      //$article = $newsModel->get_article($getVars['author']);
      $article = array('title' => "blah", 'content' => 'blah');
      //create a new view and pass it our template
      $view = new View_Model( __FUNCTION__ ,$this->model);

      //assign article data to view
      $view->assign($article);
    }
  }