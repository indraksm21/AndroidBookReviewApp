<?php
$judul = $_POST['judul'];
$pengarang = $_POST['pengarang'];
$deskripsi = $_POST['deskripsi'];
$tahun = $_POST['tahun'];
$sql = "INSERT INTO buku (id, judul, pengarang, deskripsi, tahun)" 
        . " VALUES "
        . "('NULL', '".$judul."', '".$pengarang."', '".$deskripsi."', '".$tahun."');";

$conn = mysqli_connect("localhost", "root", "", "review_buku");
if($conn):
    $simpan = mysqli_query($conn, $sql);
    if($simpan)
    {
        echo json_encode(array(
            'status'=>'berhasil'
        ));
    }
    else 
    {
        echo json_encode(array(
            'status'=>'gagal'
        ));
    }
endif;
?>
