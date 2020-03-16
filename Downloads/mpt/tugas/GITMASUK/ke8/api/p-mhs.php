<?php

include 'config.php';

	date_default_timezone_set('Asia/Jakarta');
	$jam=date("H");$menit=date("i");$detik=date("s");
	$gabung=($jam*10000)+($menit*100)+$detik;

	$nama_mhs = $_POST['nama_mhs'];
	$nomer_mahasiswa = $_POST['nomer_mahasiswa'];
	$alamat_mahasiswa = $_POST['alamat_mahasiswa'];


	$sql = "INSERT INTO mhs (id_data,nama_mhs,nomer_mahasiswa,alamat_mahasiswa) VALUES ('$gabung','$nama_mhs','$nomer_mahasiswa','$alamat_mahasiswa')";
	$query = mysqli_query($db, $sql);

	if($query) {

	} else {
		die("Gagal menyimpan perubahan...");
	}