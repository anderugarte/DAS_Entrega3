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
$image = $parametros['foto'];

#Hash password
$password = password_hash($contraseña, PASSWORD_DEFAULT);

# Sentencia SQL
$sql = "INSERT INTO Usuarios VALUES (?,?,?,?,?)";
$stmt = mysqli_prepare($con,$sql);
mysqli_stmt_bind_param($stmt,"sssss",$username,$nombre,$password,$cumple,$image);
mysqli_stmt_execute($stmt);

# Comprobar si se ha ejecutado correctamente
if (mysqli_stmt_errno($stmt)!=0) {
echo 'Error de sentencia: ' . mysqli_stmt_error($stmt);
} else { echo 'true'; }
?>