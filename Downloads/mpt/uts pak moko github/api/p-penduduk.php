<?php

include 'config.php';

	date_default_timezone_set('Asia/Jakarta');
	$jam=date("H");$menit=date("i");$detik=date("s");
	$gabung=($jam*10000)+($menit*100)+$detik;

	$id_penduduk = $_POST['id_penduduk'];
	$nama_penduduk = $_POST['nama_penduduk'];
	$ttl_penduduk = $_POST['ttl_penduduk'];
	$hp_penduduk = $_POST['hp_penduduk'];
	$alamat_penduduk = $_POST['alamat_penduduk'];


	$sql = "INSERT INTO penduduk (id_penduduk,nama_penduduk,ttl_penduduk,hp_penduduk,alamat_penduduk) VALUES ('$gabung','$nama_mhs','$nomer_mahasiswa','$alamat_mahasiswa')";
	$query = mysqli_query($db, $sql);

	if($query) {

	} else {
		die("Gagal menyimpan perubahan...");
	}