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
      <a href="dashboard.php">Dashboard</a>
      <a href="page2.php" class="active">Mexico</a>
      <a href="#about">Graph</a>
      <a href="logout.php">Logout</a>
      <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
    </div>

    <form action="/Countries/mexico.php" method="GET">
    <table style="">
      <?php
        $csv = array_map('str_getcsv', file('config/FullWeatherData.csv'));
        foreach ($csv as $var) {
            $arr[] = $var["0"];
        }
        $arr = array_slice(array_values(array_unique($arr, SORT_REGULAR)), 1);
        echo "<tr>";
        $row = '';
        for ($i=0; $i < count($arr); $i++) {
          if ($row >= 14) {
            $row = '';
            echo "</tr><tr>";
          }
            echo '<td> <div class="box">';
              echo '<input type="radio" id="station" name="station" value="' . $arr[$i] . '">' . $arr[$i];
            echo '</div> </td>';
          $row++;
        }
      ?>
    </table>
    <input type="submit" name="submit" onclick="return validate()" />
    </form>
  </body>
</html>
