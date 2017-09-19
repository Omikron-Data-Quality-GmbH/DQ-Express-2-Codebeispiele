<?php

/** 
 * Represents the api token which is returned by the authorization endpoint.
 */
class ApiToken {
	
	/**
	 * The access token is used to authorize a user to access the customer endpoint of DQ Express 2.
	 */
	private $access_token;
 
	/**
	 * Defines how long a token is valid.
	 */
	private $epxires_in;
 
	/**
	 * Defines the token type. The token type should always be "Bearer".
	 */
	private $refresh_token;
 
	/**
	 * The refresh token is used to refresh the user authorization if the access token is no longer valid.
	 */
	private $token_type;
 
	function __construct($json) { 
		$this->access_token = $json->access_token;
		$this->epxires_in = $json->expires_in;
		$this->refresh_token = $json->refresh_token;
		$this->token_type = $json->token_type;
	}
 
	public function getAccessToken() {
		return $this->access_token;
	}

	public function getExpiresIn() {
		return $this->epxires_in;
	}

	public function getRefreshToken() {
		return $this->refresh_token;
	}

	public function getTokenType() {
		return $this->token_type;
	}
}
 
?>