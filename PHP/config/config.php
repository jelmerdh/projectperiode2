<?php

//Credentials checker
function get_credentials($file) {
  $fp = fopen($file, 'r');
  while ($line = fgetcsv($fp)) {
    $lines[] = $line;
  }
  fclose($fp);

  return $lines;
}

$credentials = get_credentials('config/userdata.csv');

$check_credentials = function($username, $password) use ($credentials) {
foreach($credentials as $credential)
    if($credential[0] == $username && $credential[1] == $password)
        return true;

return false;
};
?>
