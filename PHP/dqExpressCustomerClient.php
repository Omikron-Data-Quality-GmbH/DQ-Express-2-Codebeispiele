<?php

require_once('executeModuleInputData.php');
require_once('executeModuleResult.php');

/**
 * This client is used to execute a workflow module
 */
class DqExpressCustomerClient {
	
	const CUSTOMER_SERVICE_MODULE_EXECUTION_URL = "https://<change me>/api/v1/modules/";
	const SESSION_ID_HEADER_NAME = "sessionid";
	
	private $access_token;
	
	public function __construct($token) {
		$this->access_token = $token;
	}

	/** Execute a workflow module.
     * @param moduleName The name of the module to execute
     * @param inputData The module data structure which should be passed to the workflow module
     * @param sessionId The optional session id. We can get a session id by executing a module. 
     * The session id is used to execute similar modules. For example if you call an address suggesst five times with the same address, you only have to pay once.</param>
     * @return Returns the result of the module execution.
     */
	public function executeModule($module_name, $input_data, $session_id) {
		$urlTemplate = DqExpressCustomerClient::CUSTOMER_SERVICE_MODULE_EXECUTION_URL . $module_name;
		
		if(!is_null($session_id) && $session_id !== "") {
			$urlTemplate .= "?sessionId={$session_id}";
		}
		
		$headers = array (
			'Content-Type: application/json',		
			'Authorization: Bearer ' . $this->access_token
		);
		
		$json = json_encode($input_data);
		
		$http = curl_init($urlTemplate);     
		curl_setopt($http, CURLOPT_CUSTOMREQUEST, "PUT");                                                                     
		curl_setopt($http, CURLOPT_POSTFIELDS, $json);                                                                  
		curl_setopt($http, CURLOPT_RETURNTRANSFER, true);     
		curl_setopt($http, CURLOPT_HEADER, true);		
		curl_setopt($http, CURLOPT_HTTPHEADER, $headers); 

		$response = curl_exec ($http);	
		$http_status = curl_getinfo($http, CURLINFO_HTTP_CODE);

		if(curl_errno($http) || $http_status != 200){
			throw new Exception('Something went wrong... Handle your error.');
		}

		$body = $this->getBody($http, $response);
		$headers = $this->getHeaders($http, $response);
		
		curl_close($http);
		
		$json = json_decode($body);
		
		$session_id = "";
		
		if(array_key_exists(DqExpressCustomerClient::SESSION_ID_HEADER_NAME, $headers)) {
			$session_id = $headers[DqExpressCustomerClient::SESSION_ID_HEADER_NAME];
		}
		
		return new ExecuteModuleResult($json, $session_id);
	}
	
	private function getBody($http, $response) {
		$header_size = curl_getinfo($http, CURLINFO_HEADER_SIZE);
		$body = substr($response, $header_size);
		
		return $body;
	}
	
	private function getHeaders($http, $response) {
		$header_size = curl_getinfo($http, CURLINFO_HEADER_SIZE);
		$headerString = substr($response, 0, $header_size);
		$headers=array();

		$data=explode("\n",$headerString);
		$headers['status']=$data[0];

		array_shift($data);

		foreach($data as $part){
			if(!strpos($part, ":")) {
				continue;
			}
			
			$middle=explode(":",$part);
			$headers[strtolower(trim($middle[0]))] = trim($middle[1]);
		}
		
		return $headers;
	}
}

?>