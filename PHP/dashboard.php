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
    <meta charset="utf-8">
    <figure class="highcharts-figure">
      <div id="container"></div>
      <p class="highcharts-description">
      </p>
    </figure>
  <?php
/*
Ophalen van de csv bestanden en het in een array zetten.
*/
  $csvPath = "config/TempData.csv";
  $csvData = array_map('str_getcsv', file($csvPath));
  $stnr = array_map('str_getcsv', file("config\stations.csv"));

/*
Het zoeken naar een station en het result teruggeven.
*/
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
  //Array aanmaken voor de hoogste temperaturen
  $hottestStations = array();

  function getHighestTemp($file, $array){
    /*Functie om de top 10 warmste stations te krijgen. De stations komen in
    een array die gesort wordt van hoog naar laag. */
      $stationnmmr=0;
      $highest = 0;
      $j = 1;
      /*Array eerst vullen met, begint bij 1 om de eerste regel van csv te skippen.
      Er eerst 10 waardes inzetten zodat de array wordt gevuld.*/
      while($j < 11){
          $highest = floatval($file[$j][3]);
          $stationnmmr = $file[$j][0];
          $array[$stationnmmr] = $highest;
          $j++;
      }
      /*Loopen en de temperaturen met elkaar vergelijken. Als de temperatuur hoger is dan de laagste die erin staat dan wordt die vervangen door een nieuwe
        Hierbij wordt ook gekeken als het stationnummer er al in staat. Staat de station nummer erin en is de temperatuur hogen dan wordt de hogere temperatuur erin gezet.
        Hij returned de array.
        */
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
  //Aanmaken array van de hoogste temperaturen gesort van hoog naar laag.
  $hottestArray  = getHighestTemp($csvData, $hottestStations);

  function setStationVar($array){
    $tel = 1;
    $teller = 0;
    /*
      Als één van de knoppen wordt ingedrukt zal de variabele tel veranderen naar de positie die in de line graph moet komen.
      Met de foreach loop ga je door de $hottestarray heen en als de $teller gelijk is aan $tel - 1 dan wordt de station gereturned en dan stopt hij.
      $tel - 1 wordt gebruikt omdat hij anders niet de correcte positie pakt.
    */
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
      else if(isset($_POST['setStationVar6'])){
        $tel = 6;
      }
      else if(isset($_POST['setStationVar7'])){
        $tel = 7;
      }
      else if(isset($_POST['setStationVar8'])){
        $tel = 8;
      }
      else if(isset($_POST['setStationVar9'])){
        $tel = 9;
      }
      else if(isset($_POST['setStationVar10'])){
        $tel = 10;
      }
      foreach ($array as $station => $temp) {
          if($teller == $tel - 1){
            $teststation = $station;

            return $teststation;
            break;
          }
          else{
            $teller++;

        }
      }
    }
    /*  Aanmaken van de stationvar */
  $stationvar = setStationVar($hottestArray);

/*
 Het aanmaken van een nieuwe array $dataPoints hier wordt gebruikt gemaakt van de search methode die alle data opzoekt die bij dat station hoort.
 In de foreach loop wordt iedere keer de datum en temperatuur gepushed in de datapoints array. Het javascripts gedeelte maakt gebruik van deze array.
 y staat voor de temperatuur en label voor de datum.
*/
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
  /*
  Aanmaken van de line chart.
  Hierbij wordt gebruik gemaakt van de datapoints array die eerder is aangemaakt.
  JSON_NUMERIC_CHECK encodes numeric strings als numbers.
  Hierbij is gebruikt gemaakt van canvasjs zie link:
  source:  https://canvasjs.com/php-charts/line-chart/
  */
  window.onload = function () {
    CanvasJS.addColorSet("red",
        [//colorSet Array

        "#05386B",
        ]);


  var chart = new CanvasJS.Chart("chartContainer", {
  	animationEnabled: true,
    zoomEnabled: true,
    backgroundColor: "white",
    colorSet: "red",
  	title:{
  		text: "Top 10 hottest weather",
      fontColor: "#05386B",
  	},
    /* Y as voor de temperatuur.
    */
  	axisY: {
  		title: "Temperature",
      fontColor: "#05386B",
  		suffix: "°C",


  	},
  	data: [{
  		type: "line",
  		dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
  	}]
  });

//Render de line chart
  chart.render();

  }
  </script>

  <div id="chartContainer" style="height: 370px; width: 60%;"></div>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

  <?php

    $hottestkeys  = array_keys($hottestArray);

    for ($i=0; $i < count($hottestkeys); $i++) {
      $vez[] = search($stnr, '0', $hottestkeys[$i]);

    }
    //this loop only gets the station numbers from the array $csv
    foreach ($vez as $ster) {
      $sarr[] = $ster["0"]["2"];
      $narr[] = $ster["0"]["1"];
    }
     //the array is cleared from duplicates, it skips the first value (stn) and gets reindexed
    $hotCountry = array_values($sarr);
    $hotCity = array_values($narr);
   ?>

  <form action="" method="post">
    <?php
    //this loop creates boxes with stationnumbers on which you can click on.
      $count = 1;
      //this if-statement creates a new row of there are 14 columns made.
      for ($i=0; $i < count($hottestkeys) ; $i++) {
        if (strpos($hotCity[$i], $hotCountry[$i]) !== false) {
          echo '<button type="submit"  name="setStationVar'.$i.'" class="submit button'.$i.'1"  value="1">'.$count.'. '. $hotCity[$i] .'</button>';
        }
        else {
          echo '<button type="submit"  name="setStationVar'.$i.'" class="submit button'.$i.'1"  value="1">'.$count.'. '.$hotCountry[$i]. '/'. $hotCity[$i] .'</button>';
        }

        $count++;
      }
     ?>

      </form>
</body>




</html>
