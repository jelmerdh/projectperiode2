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
    <img src="media\logo.png" alt="" style="float:left;width:7%;">
    <h1> Weather Dashboard</h1>
    <div class="topnav" id="myTopnav">
      <a href="dashboard.php"><b>Dashboard</b></a>
      <a href="page2.php"><b>Mexico</b></a>
      <a href="logout.php"><b>Logout</b></a>
      <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
    </div>

    <!-- the form is made using the GET method and sends information to mexico.php -->

    <table style="  ">
      <?php
        //this variable maps the csv file
        $csv = array_map('str_getcsv', file('config/stations.csv'));

        function search($array, $key, $value) {
            $results = array();

            // if it is array
            if (is_array($array)) {

                // if array has required key and value
                // matched store result
                if (isset($array[$key]) && $array[$key] == $value) {
                    $results[] = $array;
                }

                // Iterate for each element in array
                foreach ($array as $subarray) {

                    // recur through each element and append result
                    $results = array_merge($results,
                            search($subarray, $key, $value));
                }
            }

            return $results;
        }

        $res = search($csv, '2', "MEXICO");
        //this loop only gets the station numbers from the array $csv
        foreach ($res as $var) {
            $arr[] = $var["1"];
            $nmr[] = $var["0"];
        }
        //the array is cleared from duplicates, it skips the first value (stn) and gets reindexed
        $mexico = array_slice(array_values(array_unique($arr, SORT_REGULAR)), 0);
        $station = array_slice(array_values(array_unique($nmr, SORT_REGULAR)), 0);
        echo "<tr>";
        $row = '';
        //this loop creates boxes with stationnumbers on which you can click on.
        for ($i=0; $i < count($mexico); $i++) {
          //this if-statement creates a new row of there are 14 columns made.
          if ($row >= 14) {
            $row = '';
            echo "</tr><tr>";
          }
            echo '<td><form action="Countries/mexico.php" method="GET">';
              echo '<input type="hidden" id="city" name="city" value="'. $mexico[$i] .'">';
              echo '<button type="submit" class="box" id="station" name="station" value="' . $station[$i] . '">' . $mexico[$i] . '</button>';
            echo '</form></td>';
          $row++;
        }
      ?>
    </table>

  </body>
</html>
