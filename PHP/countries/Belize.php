<!DOCTYPE html>
<?php
// Initialize the session
session_start();

// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: ../index.php");
    exit;
}
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
          <h1>Weather</h1>
          <h2>Belize</h2>
          <h3>Tuesday 26 January</h3>
          <ul>

          </ul>
          <a href="../dashboard.php" style="text-decoration:none;color:white;border: 1px solid white;">Dashboard</a>
          <div class="leftside">
            <p style="font-size:50px">27 &#176;</p>
            <p>Mostly sunny</p>
          </div>

          <div class="rightside">
            <table style="width:100%;border-spacing:30px">
              <tr>
                <td>Air pressure</td>
                <td>Dew point</td>
                <td>Visibility</td>
              </tr>
              <tr>
                <td>Precipitation</td>
                <td>Snow depth</td>
                <td>Cloud cover</td>
              </tr>
              <tr>
                <td>Wind direction</td>
                <td>Wind speed</td>
                <td>Weather events</td>
              </tr>
            </table>
          </div>

        </div>

        <!-- menubar -->
        <div class="bottom">
          <ul>
            <li><a href="#" class="active">history</a></li>
          </ul>
          <br>
          <div class="boxes">
            <div class="box">
              Weather 1
            </div>
            <div class="box">
              Weather 2
            </div>
            <div class="box">
              Weather 3
            </div>
            <div class="box">
              Weather 4
            </div>
            <div class="box">
              Weather 5
            </div>
            <div class="box">
              Weather 6
            </div>
            <div class="box">
              Weather 7
            </div>
          </div>
        </div>
      </div>


    </div>

  </body>
</html>
