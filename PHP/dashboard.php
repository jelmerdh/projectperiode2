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
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel='stylesheet' href='style.css' />
    <h1> Weather Dashboard</h1>
    <title>Weather Dashboard</title>
</head>
<body>
      <div class="darkmodebutton">

      </div>
      <div class="wrapper">
        <button type="button" id="logout"><a href="logout.php">Logout</a></button>
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


</body>




</html>
