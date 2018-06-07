<?php header('Access-Control-Allow-Origin: *'); ?>

<?php 

include "EIConfig.php";
include "misc.php";
include "EIRequest.php";
include "EIApps.php";
include "EIExamples.php";


println("<ei_response>");
try {
  println("<ei_server_output>");
  $request = new EIRequest();
  $response = $request->process();
  println("</ei_server_output>");
  $date = date('Y-m-d H:i:s');
  $response = preg_replace("/(<\?xml)(.)*\?>/" , '', $response); // this should be fixed in another way
  
  println("<ei_output>");
  println( $response );
  println("</ei_output>");
  $resp = "<ei_response>" ."<ei_output>" . $response . "</ei_output>" . "</ei_response>";
  file_put_contents("/tmp/response","\n".$date." ".print_r($resp,true)."\n",FILE_APPEND);
} catch (Exception $e) {
  println("</ei_server_output>");
  print("<ei_error>");
  print( $e->getMessage() );
  println("</ei_error>");
   $resp = "<ei_response>" ."<ei_server_output>" . "</ei_server_output>" . "<ei_error>" . $e->getMessage() . "</ei_error>" . "</ei_response>";
  file_put_contents("/tmp/response","\n".$date." ".print_r($resp,true)."\n",FILE_APPEND);
}
println("</ei_response>");

?>
