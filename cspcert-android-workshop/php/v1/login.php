<?php 
	session_start();
	// Comparing passwords as plain text is a no no. Don't do this! This was meant to be unsecure anyways for a workshop event.
	if (isset($_SESSION['username']) && isset($_SESSION['password'])) {
		echo $_SESSION['username'];
		echo $_SESSION['password'];
		if ($_SESSION['username'] == 'administrator' && $_SESSION['password'] == 'mypassword') {
			header("Location: flag.php");
		} else {
			$_SESSION['username'] = NULL;
			$_SESSION['password'] = NULL;
		}
	} else {
		if (isset($_POST['username']) && isset($_POST['password'])) {
			if ($_POST['username'] == 'administrator' && $_POST['password'] == 'mypassword') {
				$_SESSION['username'] = $_POST['username'];
				$_SESSION['password'] = $_POST['password'];
				header("Location: flag.php");
			} else {
				$_POST['username'] = NULL;
				$_POST['password'] = NULL;
			}
		}
	}
?>
<!DOCTYPE HTML>
<head>	
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
  	<link rel="stylesheet" type="text/css" href="../../css/font-family.css">
	<link rel="stylesheet" type="text/css" href="../../css/t_login.css">
	<title>Login</title>
</head>
<body>
	<div id="container">	
		<div id="header"></div>
		<div id="content">
			<div class="container">
 				<form id="form" method="post">
 					<br>
 					<div class="row">
 						<div class="col-lg-4"></div>
                        <div class="col-lg-4">
                        	<div class="form-group">
		     					<label for="text">USERNAME:</label>
		    				 	<input type="text" class="form-control" id="text" name="username" placeholder="Enter Username" autofocus required>
		   				 	</div>
                        </div>
                    </div>
   					<div class="row">
 						<div class="col-lg-4"></div>
                        <div class="col-lg-4">
                        	<div class="form-group">
		      					<label for="pwd">PASSWORD:</label>
		    				  	<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password" required>
		    				</div>	
                        </div>
                    </div>
   					<div class="row">
 						<div class="col-lg-4"></div>
                        <div class="col-lg-4">
                        	<button type="submit" class="btn btn-default" id="btn_log" formaction="login.php">Login</button>
                        </div>
                    </div>
  				</form>
			</div>
		</div>
		<script src="../../js/jquery.min.js"></script>
  	    <script src="../../js/bootstrap.min.js"></script>
	</body>
</html>