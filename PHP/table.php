<!DOCTYPE html>
<html>
<head>
    <link rel='stylesheet' href='style.css' />
    <h1> Weather Table</h1>
    <title>Weather Table</title>
</head>
<body>

<!-- moet systeem in voor weten of ingelogd -->
<?php 
$loggedin = true; 
if ($loggedin == FALSE)
    header("Location: login.php")
?>

<ul>
    <li><a href="login.php">log in</a></li>
    <li><a href="index.php"> Dashboard</a></li>
</ul>
<br><br>
<?php
// info nodig db
$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
  die("Error because: " . $conn->connect_error);
}
// naam database nodig
$sql = "SELECT * FROM ???????";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // namen rijen database nodig voor opzetten table
  // echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
  while($row = $result->fetch_assoc()) {
    echo "";
  }
} else {
  echo "0 results";
}
$conn->close();
?>







</body>




</html>