<?php
$id = $_GET['id'];
$conn = mysqli_connect("localhost", "root", "", "review_buku");
$query = mysqli_query($conn,
        "DELETE FROM buku WHERE id = '".$id."'"
    );

if ($query) {
    echo json_encode (array(
        'status' => 'Data Terhapus'
    ));
} else {
    echo json_encode (array(
        'status' => 'Data Gagal Dihapus'
    ));
}

?>