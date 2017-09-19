/**
 * Represents the api token which is returned by the authorization endpoint.
 */
class ApiToken {
	constructor(data) {
		/**
		 * The access token is used to authorize a user to access the customer endpoint of DQ Express 2.
		 */
		this.access_token = "";
		
	   /**
		 * Defines how long a token is valid.
		 */
		this.expires_in = 0;
		
		/**
		 * Defines the token type. The token type should always be "Bearer".
		 */
		this.token_type = "";
		
		/**
		 * The refresh token is used to refresh the user authorization if the access token is no longer valid.
		 */
		this.refresh_token = "";
		
		Object.assign(this, data);	
	}
}