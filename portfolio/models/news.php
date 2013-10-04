<?php
  /**
   * The News Model does the back-end heavy lifting for the News Controller
   */
  class News_Model extends Base_Model
  {
    /**
     * Holds instance of database connection
     */
    private $db;

    public function __construct()
    {
      $this->db = new MysqlImproved_Driver;
    }

    /**
     * Fetches article based on supplied name
     *
     * @param string $author
     *
     * @return array $article
     */
    public function get_article($author)
    {
      //connect to database
      $this->db->connect();

      //sanitize data
      $author = $this->db->escape($author);

      //prepare query
      $this->db->prepare
          (
            "
        SELECT
            `date`,
            `title`,
            `content`,
            `author`
        FROM
            `articles`
        WHERE
            `author` = '$author'
        LIMIT
            1
        ;
        "
          );

      //execute query
      $this->db->query();

      $article = $this->db->fetch('array');

      return $article;
    }

  }