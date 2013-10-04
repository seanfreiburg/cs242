<html>
<head></head>
<body>
<h1>Project index </h1>
<hr/>
<h2>News</h2>
<?php foreach ($data['projects'] as $project) { ?>
  <h4><?= $project['title']; ?></h4>

  <p><?= $project['content']; ?></p>
<?php } ?>
</body>
</html>