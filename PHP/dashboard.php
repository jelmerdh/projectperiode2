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
      <a href="page2.php">Mexico</a>
      <a href="#about">Graph</a>
      <a href="logout.php">Logout</a>
      <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
    </div>

    <form method="post">
      <button type="submit" name"setStationVar1" class="submit button1"  value="setStationVar1">Station 1 </button>
      <button type="submit" name"setStationVar2" class="submit button2"  value="setStationVar2">Station 2 </button>
      <button type="submit" name"setStationVar3" class="submit button3"  value="setStationVar3">Station 3 </button>
      <button  type="submit" name"setStationVar4" class="submit button4"  value="setStationVar4">Station 4 </button>
      <button  type="submit" name"setStationVar5" class="submit button5"  value="setStationVar5">Station 5 </button>
      <button  type="submit" name"setStationVar5" class="submit button6"  value="setStationVar5">Station 6 </button>
      <button  type="submit" name"setStationVar5" class="submit button7"  value="setStationVar5">Station 7 </button>
      <button  type="submit" name"setStationVar5" class="submit button8"  value="setStationVar5">Station 8 </button>
      <button  type="submit" name"setStationVar5" class="submit button9"  value="setStationVar5">Station 9 </button>
      <button  type="submit" name"setStationVar5" class="submit button10"  value="setStationVar5">Station 10 </button>

    </form>
    <meta charset="utf-8">
    <figure class="highcharts-figure">
      <div id="container"></div>
      <p class="highcharts-description">
      </p>
    </figure>
    </body>
  <?php
  $csvPath = "config/TempData.csv";
  $csvData = array_map('str_getcsv', file($csvPath));

  function search($array, $key, $value) {
        $results = array();
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
    #var_dump($csvData);

  $hottestStations = array();

  function getHighestTemp($file, $array){
    /*Functie om de top 10 warmste stations te krijgen. De stations komen in
    een array die gesort wordt van hoog naar laag. */
      $stationnmmr=0;
      $highest = 0;
      $j = 1;
      while($j < 11){ //Array eerst vullen met, begint bij 1 om de eerste regel van csv te skippen.
          $highest = floatval($file[$j][3]);
          $stationnmmr = $file[$j][0];
          $array[$stationnmmr] = $highest;
          $j++;
      }
      for($i=$j;$i<count($file);$i++)
      {
        $highest = floatval($file[$i][3]);
        $stationnmmr = $file[$i][0];
        $min = min($array);
        $key = array_search($min, $array);

          if($highest > floatval($min)){
            if(array_key_exists($stationnmmr ,$array)){

              if(floatval($array[$stationnmmr]) < $highest){
                $array[$stationnmmr] = $highest;
              }
              continue;
            }
            unset($array[$key]);
            $array[$stationnmmr] = $highest;
          }
      }
      arsort($array);
      #var_dump($array);
      #print("<br>");
      return $array;
  }
  $hottestArray  = getHighestTemp($csvData, $hottestStations);

  function setStationVar($array){
  $tel = 1;
  $teller = 0;
   if(isset($_POST['setStationVar1'])){
      $tel = 1;
    }
    else if(isset($_POST['setStationVar2'])){
      $tel = 2;
    }
    else if(isset($_POST['setStationVar3'])){
      $tel = 3;
    }
    else if(isset($_POST['setStationVar4'])){
      $tel = 4;
    }
    else if(isset($_POST['setStationVar5'])){
      $tel = 5;
    }
    #print("$tel<br>");

    foreach ($array as $station => $temp) {
        if($teller == $tel-1){
          $teststation = $station;
          #print("$teststation");
          return $teststation;
          break;
        }
        else{
          $teller++;
          #print("$teller<br>");

      }
  }
  }
  $stationvar = setStationVar($hottestArray);

  #$station = getHighestTemp($csvData, $hottestStations);
  echo "<br>";
  #var_dump($station);

    $dataPoints = array();
    $res = search($csvData, '0', $stationvar);
    foreach ($res as $var) {
      #echo $var["0"]." - ".$var["1"]." - ".$var["2"]. " - "  .$var['3'] . "<br>";
      $x = array("y" => $var[3] , "label" => $var[1]);
      array_push($dataPoints, $x);

  }

  ?>

  <script>
  function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }
  </script>
  <script>
  window.onload = function () {


  var chart = new CanvasJS.Chart("chartContainer", {
  	animationEnabled: true,
    zoomEnabled: true,
  	title:{
  		text: ""
  	},
  	axisY: {
  		title: "Temperature",

  		suffix: "°C",


  	},
  	data: [{
  		type: "line",
  		dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
  	}]
  });


  chart.render();

  }
  </script>

  <div id="chartContainer" style="height: 370px; width: 60%;"></div>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

</body>




</html>
