<?php

$cari = $_GET['q'];
$conn = mysqli_connect("localhost", "root", "", "review_buku");
$query = mysqli_query($conn, 
        "SELECT * FROM buku WHERE judul like '%".$cari."%'"
    );
$data = array();
while ($hasil = mysqli_fetch_array($query)) {
    $data[] = array (
        'id'=>$hasil['id'],
        'judul'=>$hasil['judul'],
        'tahun'=>$hasil['tahun']
    );
}

echo json_encode(array(
    'hasil'=>$data
));
?>