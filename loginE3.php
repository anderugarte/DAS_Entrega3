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
$password = $parametros["password"];

#Ejecutar sentencia SQL
$resultado = mysqli_query($con, "SELECT Password FROM Usuarios WHERE Username ='$username'");

# Comprobar si se ha ejecutado correctamente
if (!$resultado) {
    echo 'Ha ocurrido algún error: ' . mysqli_error($con);
} else {
    #Acceder al resultado
    $fila = mysqli_fetch_row($resultado);
    $hash = $fila[0];

    if (password_verify($password, $hash)) {
        echo 'logOK';
    } else {
        echo 'password introducida: ' . $password ."\n";
        echo 'password DB: ' . $hash;

    }

}

mysqli_close($con);

?>

