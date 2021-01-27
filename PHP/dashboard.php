<!DOCTYPE html>
<?php
// Initialize the session
session_start();

// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: index.php");
    exit;
}
?>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel='stylesheet' href='style.css' />
  <title>Weather Dashboard</title>
</head>
<body>

  <div class="wrapper">
    <h1> Weather Dashboard</h1>
    <div class="topnav" id="myTopnav">
      <a href="dashboard.php" class="active">Dashboard</a>
        <div class="dropdown">
          <button class="dropbtn">Countries &#9776;
          <i class="fa fa-caret-down"></i>
          </button>
          <div class="dropdown-content">
            <a href="countries\belize.php">Belize</a>
            <a href="countries\costarica.php">Costa Rica</a>
            <a href="countries\elsavador.php">El Savador</a>
            <a href="countries\guatemala.php">Guatemala</a>
            <a href="countries\mexico.php">Mexico</a>
            <a href="countries\honduras.php">Honduras</a>
            <a href="countries\nicaragua.php">Nicaragua</a>
            <a href="countries\panama.php">Panama</a>
          </div>
        </div>
        <a href="#about">Graph</a>
        <a href="logout.php">Logout</a>
        <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
    </div>

        <!-- data nodig query, bij WHERE moet hoe we 7 dagen moeten kijken -->


        <!-- select STN, TEMP FROM WEATHERDATA WHERE ORDER BY TEMP LIMIT 10-->
        <h2>Top ten temperatures in Middle America in the last 7 days.</h2>
        <table style="width:50%">
            <tr>
                <th>ranking</th>
                <th>location</th>
                <th>temperature</th>

            </tr>
            <tr>
                <th> #1 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #2 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #3 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #4 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #5 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #6 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #7 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #8 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #9 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
            <tr>
                <th> #10 </th>
                <td> loc </td>
                <td> temp </td>
            </tr>
        </table>

  </div>

  <script>
  function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }
  s</script>
</body>




</html>
