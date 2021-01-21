<!DOCTYPE html>
<html>
<head>
    <link rel='stylesheet' href='style.css' />
    <h1> Weather Dashboard</h1>
    <title>Weather Dashboard</title>
</head>
<body>
    <div class="darkmodebutton">
        
    </div>

<!-- moet systeem in voor weten of ingelogd -->
<?php 
$loggedin = true; 
if ($loggedin == FALSE)
    header("Location: login.php")
?>
<ul>
    <li><a href="login.php">log in</a></li>
    <li><a href="table.php"> all numbers</a></li>
</ul>
<br><br>
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