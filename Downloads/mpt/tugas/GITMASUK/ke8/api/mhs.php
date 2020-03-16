<?php

include("config.php");



$sql = "SELECT * FROM mhs";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id_data' => $row['id_data'],
    	'nama_mhs' => $row['nama_mhs'],
    	'nomer_mahasiswa' => $row['nomer_mahasiswa'],
    'alamat_mahasiswa' => $row['alamat_mahasiswa']
));
}
echo json_encode(array("result" => $result));
?>



