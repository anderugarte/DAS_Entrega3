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

#Exitosa -> primero coger los parametros de los edittext
$parametros = json_decode(file_get_contents('php://input'), true);
$username = $parametros["username"];

#Ejecutar sentencia SQL para comprobar si ya existe ese nombre de usuario
$resultado = mysqli_query($con, "SELECT Username FROM Usuarios WHERE Username='$username'");

# Comprobar si se ha ejecutado correctamente
if (!$resultado) {
    echo 'Ha ocurrido algún error: ' . mysqli_error($con);
}

#Acceder al resultado
$fila = mysqli_fetch_row($resultado);

# Generar el array con los resultados con la forma Atributo - Valor
$arrayresultados = array(
'Usuario' => $fila[0]
);

// Comprobar si existe un usuario con ese username
if (strlen($fila[0])==0) { 
    echo 'noexiste'; // no existe
} else {
    echo 'existe'; // existe
}



?>
