<?php 
	if (isset($_POST['pin'])) {
		if ($_POST['pin'] == "6754") {
			echo 'FLAG{brute_forcing_the_pin_is_the_way_in}';
		} else {
			echo 'You are not authorized to view this page';
		}
	} else {
		echo 'You are not authorized to view this page';
	}
?>