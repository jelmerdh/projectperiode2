<!DOCTYPE html>
<?php
// Initialize the session
session_start();

// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: ../index.php");
    exit;
}

//this variable maps the csv file in an array
$csv = array_map('str_getcsv', file('../config/FullWeatherData.csv'));
$station = array_map('str_getcsv', file('../config/stations.csv'));

//this function is used to find specific items in a multidimensional array
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
//checks if the submit button has been clicked
if (isset($_GET['station'])) {
//this variable gets the information from the pag2.php form
if ($_GET['station'] == NULL) {
  header("location: ../page2.php");
}
else {
  $currentstation = $_GET['station'];
  $currentcity = $_GET['city'];
}

//the function is used to find the $currentstation in the array $csv
$res = search($csv, '0', $currentstation);
foreach ($res as $var) {
    //$dataArray is filled with every information about the correlating station
    $dataArray[] = array($var["0"], $var['1'], $var['2'], $var['3'],
                         $var["4"], $var['5'], $var['6'], $var['7'],
                         $var["8"], $var['9'], $var['10'], $var['11'],
                         $var['10'], $var['11']);
}

//$lastData is the array with the latest weather information
$lastData = end($dataArray);

$country = search($station, '0', $currentstation);

foreach ($country as $zar) {
  $name[] = $zar["1"];
}

$lastCountry = end($name);

//function to change the date to the past
function changeDate($lastData, $day){
    // explode data into year month day
    $slicedData = explode("-", $lastData);
    switch($day){
      case 0:
        $slicedData[2] = $slicedData[2] - 1;
        break;
      case 1:
        $slicedData[2] = $slicedData[2] - 2;
        break;
      case 2:
        $slicedData[2] = $slicedData[2] - 3;
        break;
    }
    // add the changed data together
    $finishedData = $slicedData[0] . "-" . $slicedData[1] . "-" . $slicedData[2];
    return $finishedData;
}


?>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Mexico Stations</title>

    <link rel="stylesheet" href="../style2.css">
  </head>
  <body>
    <div class="wrapper">
      <div class="header">

      </div>
        <!-- Upper Half -->
        <div class="upper">
          <img src="../media\logo.png" alt="" style="float:left;width:7%;">
          <h1>Weatherstation <?php echo $currentcity ?></h1>
          <h2>Mexico</h2>
          <h3><?php echo $lastData[1] ?> <?php echo $lastData[2] ?></h3>
          <div class="leftside">
            <p style="font-size:50px"><?php echo $lastData[3] ?> &#176;C</p>
          </div>

          <div class="rightside">
            <ul>
              <li><a href="../page2.php" class="active">Stations</a></li>
              <li><a href="../dashboard.php">Dashboard</a></li>
            </ul>
            <table style="width:100%;border-spacing:30px">
              <tr>
                <td><b>Dew point:<br><?php echo $lastData[4] ?> &#176;C</b></td>
                <td><b>Station Level Pressure: <br><?php echo $lastData[5] ?> m</b></td>
                <td><b>Sea Level Pressure: <br><?php echo $lastData[6] ?> m</b></td>
              </tr>
              <tr>
                <td><b>Visibility:<br><?php echo $lastData[7] ?> km</b></td>
                <td><b>Wind Speed:<br><?php echo $lastData[8] ?> km/h</b></td>
                <td><b>Percipitation:<br><?php echo $lastData[9] ?> cm</b></td>
              </tr>
              <tr>
                <td><b>Events:<br>
                  <?php if ($lastData[10]=="000000") {echo "none"; }
                  else {echo $lastData[10];}
                  ?></td></b>
                <td><b>Cloud Cover:<br><?php echo $lastData[11] ?></b></td>
                <td><b>Wind Direction:<br><?php echo $lastData[12] ?></td></b>
              </tr>
            </table>
          </div>
        </div>
        <!-- bottom Half -->
        <div class="bottom">
          <form method="GET">

          <br><br><br>
          <table>
          <?php
            for ($i=0; $i < 3 ; $i++) {
              //sets the date of the boxes at the bottom for days of past data
              switch ($i){
                case 0:
                  $textshown = changeDate($lastData[1], 0);
                  break;
                case 1:
                  $textshown = changeDate($lastData[1], 1);
                  break;
                case 2:
                  $textshown = changeDate($lastData[1], 2);
                  break;
              }
              echo '<td><form action="mexicopast.php" method="GET">';
                echo '<input type="hidden" id="city" name="city" value="'. $currentcity .'">';
                echo '<input type="hidden" id="day" name="day" value="'. $textshown .'">';
                echo '<button type="submit" class="box" id="station" name="station" value="' . $lastData[0] . '">' . $textshown . '</button>';
              echo '</form></td>';
            }
           ?>
         </table>
         </form>
        </div>
      </div>
    </div>
    <?php } ?>
  </body>
</html>
