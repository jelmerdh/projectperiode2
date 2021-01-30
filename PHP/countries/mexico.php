<!DOCTYPE html>
<?php
// Initialize the session
session_start();

// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: ../index.php");
    exit;
}

$csv = array_map('str_getcsv', file('../config/FullWeatherData.csv'));

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

$currentstation = $_GET['station'];


if (isset($_POST['submit'])) {
  $station = '';
  if (isset($_POST['station'])) {
    $station = $_REQUEST['station'];

  }

  if ($station == NULL) {
    echo "please suck a dick";
    echo "alert()";
    $currentstation = '769043';
  }

  else {
    $currentstation = $station;
  }
}



$res = search($csv, '0', $currentstation);
foreach ($res as $var) {
    //echo $var["0"]." - ".$var['3'] . "<br>";
    $dataArray[] = array($var["0"], $var['1'], $var['2'], $var['3'],
                         $var["4"], $var['5'], $var['6'], $var['7'],
                         $var["8"], $var['9'], $var['10'], $var['11'],
                         $var['10'], $var['11']);
}

//var_dump($dataArray);
$lastData = end($dataArray);

//echo $arr[1];

?>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Design Calvin</title>

    <link rel="stylesheet" href="../style2.css">
  </head>
  <body>
    <div class="wrapper">
      <div class="header">

      </div>

      <div class="body">
        <div class="upper">
          <h1>Weatherstation <?php echo $currentstation ?></h1>
          <h2>Mexico</h2>
          <h3><?php echo $lastData[1] ?> <?php echo $lastData[2] ?></h3>
          <div class="leftside">
            <p style="font-size:50px"><?php echo $lastData[3] ?> &#176;C</p>
            <p>Mostly sunny</p>
          </div>

          <div class="rightside">
            <ul>
              <li><a href="#" class="active">Countries</a></li>
              <li><a href="../dashboard.php">Dashboard</a></li>
            </ul>
            <table style="width:100%;border-spacing:30px">
              <tr>
                <td>Dew point:<br><?php echo $lastData[4] ?> &#176;C</td>
                <td>Station Level Pressure: <br><?php echo $lastData[5] ?> m</td>
                <td>Sea Level Pressure: <br><?php echo $lastData[6] ?> m</td>
              </tr>
              <tr>
                <td>Visibility:<br><?php echo $lastData[7] ?> km</td>
                <td>Wind Speed:<br><?php echo $lastData[8] ?> km/h</td>
                <td>Percipitation:<br><?php echo $lastData[9] ?> cm</td>
              </tr>
              <tr>
                <td>Events:<br>
                  <?php if ($lastData[10]=="000000") {echo "none"; }
                  else {echo $lastData[10];}
                  ?></td>
                <td>Cloud Cover:<br><?php echo $lastData[11] ?></td>
                <td>Wind Direction:<br><?php echo $lastData[12] ?></td>
              </tr>
            </table>
          </div>
        </div>
        </div>
      </div>
    </div>
    <script>
      function alert() {
        alert("Kies aub een station....");
      }
    </script>
  </body>
</html>
