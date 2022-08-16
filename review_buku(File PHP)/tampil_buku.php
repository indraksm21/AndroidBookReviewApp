<?php

$conn = mysqli_connect("localhost", "root", "", "review_buku");
$sql = mysqli_query($conn, "SELECT * FROM buku ORDER BY judul asc");
$data = array();
while ($tampil_data = mysqli_fetch_array($sql)):
    $data[] = array(
        'id' =>$tampil_data['id'],
        'judul' =>$tampil_data['judul'],
        'pengarang' =>$tampil_data['pengarang'],
        'deskripsi' =>$tampil_data['deskripsi'],
        'tahun' =>$tampil_data['tahun']
    );
endwhile;

echo json_encode(array(
    'hasil' =>$data
));

?>