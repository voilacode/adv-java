<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DictionaryApp</title>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  </head>
  <body>
  <div class="container mt-5">
  <h1 class="text-center text-danger mb-4">DICTIONARY APP</h1>
  <div class=" row justify-content-center">
  <div class="col-md-6">
  <form class="input-group mb-3" action="DictionaryServlet" method="post" >
  <input type="text" class="form-control shadow" name="word" placeholder="Enter a word">
  <div class="input-group-append">
  <button type="submit" class="btn btn-primary">Search</button>
  </div>
  </form>
  
  </div>
  </div>
  </div>
  <!-- --- -->
  
  <div class="row justify-content-center">
  <div class="col-md-6">
  <div class="card border-info mb-3">
  <div class="card-body shadow">
  <h4 class="card-title"><span class="text-danger">Word:</span> <span class="text-primary">${word}</span></h4>
  <p class="card-text fs-5">${definition}</p>
  </div>
  </div>
  </div>
  </div>
  
   
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>