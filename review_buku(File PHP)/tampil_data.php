<?php
$id = $_GET['id'];
$conn = mysqli_connect("localhost", "root", "", "review_buku");
$query = mysqli_query($conn,
        "SELECT * FROM buku WHERE id = '".$id."'");

$hasil = mysqli_fetch_array($query);
echo json_encode (array(
    'judul'=>$hasil['judul'],
    'pengarang'=>$hasil['pengarang'],
    'deskripsi'=>$hasil['deskripsi'],
    'tahun'=>$hasil['tahun']
));
?>