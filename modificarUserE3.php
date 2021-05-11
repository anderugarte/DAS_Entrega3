<?php
$DB_SERVER="localhost";
$DB_USER="Xigonzalez274";
$DB_PASS="X1mEGdrqm";
$DB_DATABASE="Xigonzalez274_Bichotes";

#Se establece la conexión:
$con = mysqli_connect($DB_SERVER, $DB_USER, $DB_PASS, $DB_DATABASE);

#Comprobamos conexión:
#Errónea
if (mysqli_connect_errno($con)) {
echo 'Error de conexion: ' . mysqli_connect_error();
exit();
}

#Exitosa
$parametros = json_decode(file_get_contents('php://input'), true);
$username = $parametros['username'];
$nombre = $parametros['nombre'];
$password = $password['password'];
$cumple = $parametros['cumple'];
$iamge = $parametros['foto'];

# Sentencia SQL
$sql = "UPDATE Usuarios SET Username = $username, Nombre = $nombre, Password = $password, Cumple = $cumple, Foto = $image WHERE Username = $username";

#Ejecutar sentencia SQL para hacer UPDATE
if (mysqli_query($con, $sql)) {
    echo "done";
} else {
    echo "fail" . mysqli_error($con);
}
 mysqli_close($con);


?>