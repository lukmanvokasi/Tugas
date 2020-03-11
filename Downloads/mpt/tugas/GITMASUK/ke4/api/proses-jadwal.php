<?php

include 'config.php';

	$shubuh = $_POST['shubuh'];
	$dhuhur = $_POST['dhuhur'];
	$ashar = $_POST['ashar'];
	$maghrib = $_POST['maghrib'];
	$isha = $_POST['isha'];
	$dhuha = $_POST['dhuha'];

	$sql = "UPDATE jadwal_sholat SET shubuh='$shubuh', dhuhur='$dhuhur', ashar='$ashar', maghrib='$maghrib', isha='$isha', dhuha='$dhuha'";
	$query = mysqli_query($db, $sql);

	if($query) {

	} else {
		die("Gagal menyimpan perubahan...");
	}