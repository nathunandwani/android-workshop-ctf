<?php
	session_start(); 
	if (isset($_SESSION['username']) && isset($_SESSION['password'])) {
		if ($_SESSION['username'] == 'administrator' && $_SESSION['password'] == 'mypassword') {
			echo "FLAG{hardcoded_credentials_still_exist!}";
			$_SESSION['username'] = NULL;
			$_SESSION['password'] = NULL;
		} else {
			$_SESSION['username'] = NULL;
			$_SESSION['password'] = NULL;
			header("Location: login.php");
		}
	} else {
		$_SESSION['username'] = NULL;
		$_SESSION['password'] = NULL;
		header("Location: login.php");
	}
?>