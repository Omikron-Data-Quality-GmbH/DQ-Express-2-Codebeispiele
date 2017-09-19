<?php

/**
 * This client is used to authorize a customer to the endpoint of DQ Express 2.
 */
class DqExpressAuthorizationClient {
 
	/**
	 * The oauth client id and the secret are used to authenticate the client.
	 * Only trusted clients should be able to call the customer endpoint.
	 * Please keep in mind, that you never publish the client id and secret to untrusted sources.
	 */
	const OAUTH_CLIENT_ID = "<change me>";
	const OAUTH_CLIENT_SECRET = "<change me>";

	/**
	 * The oauth scopes are defining which resources the user can access.
	 */
	const OAUTH_SCOPES = array(
	/**
	 * With this scope, an user is able to access the customer service endpoint.
	 */
	"Omikron.DataQuality.Express.CustomerService.Api",
	/**
	 * With this scope, an expired token can be refreshed. 
	 * An access token expires in 20 minutes. If the token is expired, we can`t access the customer endpoint with the same access token anymore.
	 * You have to request an new access token using the refresh token.
	 */
	"offline_access"
	);

	/**
	 * The authorization endpoint which is used to authorize the user and retrieve an api access token.
	 */	
	const AUTHORIZATION_SERVICE_URL = "https://<change me>/connect/token";

	/**
	 * Authorize the user and returns an new access token.
	 */	
	public static function authorize($username, $password) {	 
		$data = array(
			'grant_type' => 'password',
			'scope' => implode(" ", DqExpressAuthorizationClient::OAUTH_SCOPES),
			'client_id' => DqExpressAuthorizationClient::OAUTH_CLIENT_ID,
			'client_secret' => DqExpressAuthorizationClient::OAUTH_CLIENT_SECRET,
			'username' => $username,
			'password' => $password
		);
		 
		$from_url_encoded = http_build_query($data);
		 
		$headers = array(
			'Content-Type: application/x-www-form-urlencoded'
		);

		$http = curl_init(DqExpressAuthorizationClient::AUTHORIZATION_SERVICE_URL);     
		curl_setopt($http, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
		curl_setopt($http, CURLOPT_POSTFIELDS, $from_url_encoded);                                                                  
		curl_setopt($http, CURLOPT_RETURNTRANSFER, true);                                                                      
		curl_setopt($http, CURLOPT_HTTPHEADER, $headers); 

		$server_output = curl_exec ($http);	
		$http_status = curl_getinfo($http, CURLINFO_HTTP_CODE);

		if(curl_errno($http) || $http_status != 200){
			throw new Exception('Something went wrong... Handle your error.');
		}

		curl_close($http);

		$json = json_decode($server_output);
		return new ApiToken($json);	
	}	 

	/**
	 * Authorize the user and returns an new access token.
	 */
	public static function refreshAuthorization($refresh_token) {
		$data = array(
			'grant_type' => 'refresh_token',
			'client_id' => DqExpressAuthorizationClient::OAUTH_CLIENT_ID,
			'client_secret' => DqExpressAuthorizationClient::OAUTH_CLIENT_SECRET,
			'refresh_token' => $refresh_token
		);
		 
		$from_url_encoded = http_build_query($data);
		 
		$headers = array(
			'Content-Type: application/x-www-form-urlencoded'
		);

		$http = curl_init(DqExpressAuthorizationClient::AUTHORIZATION_SERVICE_URL);     
		curl_setopt($http, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
		curl_setopt($http, CURLOPT_POSTFIELDS, $from_url_encoded);                                                                  
		curl_setopt($http, CURLOPT_RETURNTRANSFER, true);                                                                      
		curl_setopt($http, CURLOPT_HTTPHEADER, $headers); 

		$server_output = curl_exec ($http);	
		$http_status = curl_getinfo($http, CURLINFO_HTTP_CODE);

		if(curl_errno($http) || $http_status != 200){
			throw new Exception('Something went wrong... Handle your error.');
		}

		curl_close($http);

		$json = json_decode($server_output);
		return new ApiToken($json);		 
	}
}
?>