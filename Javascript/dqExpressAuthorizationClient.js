/**
 * The oauth client id and the secret are used to authenticate the client.
 * Only trusted clients should be able to call the customer endpoint.
 * Please keep in mind, that you never publish the client id and secret to untrusted sources.
 * WARNING: This javascript code is only for illustration. In production, NEVER authorize in javascript client code.
 * The authorization should always be executed from trusted sources (e.g. php backend).
 */
const oauthClientId = "<change me>";
const oauthClientSecret = "<change me>";

/**
 * The oauth scopes are defining which resources the user can access.
 */
const oauthScopes = [
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
];

/**
 * The authorization endpoint which is used to authorize the user and retrieve an api access token.
 */
const authorizationServiceUrl = "https://<change me>/connect/token";

/**
 * Authorize the user and returns an new access token.
 */
function authorize(username, password, onSuccessCallback) {
	let http = new XMLHttpRequest();
	http.open("POST", authorizationServiceUrl, true);
	http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			let json = JSON.parse(http.responseText);
			onSuccessCallback(new ApiToken(json));
		} else if(http.readyState == 4 && http.status != 200) {
			console.log("Something went wrong... Handle your error.");
		}
	}
	
	let parameters = buildAuthorizeUrlParameters(username, password);
	http.send(parameters);
}

/**
 * Refresh the authorization token using a valid refresh token.
 */
function refreshAuthorization(refreshToken, onSuccessCallback) {
	let http = new XMLHttpRequest();
	http.open("POST", authorizationServiceUrl, true);
	http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			let json = JSON.parse(http.responseText);
			onSuccessCallback(json);
		} else if(http.readyState == 4 && http.status != 200) {
			console.log("Something went wrong... Handle your error.");
		}
	}
	
	let parameters = buildRefreshTokenUrlParameters(refreshToken);
	http.send(parameters);
}

function buildAuthorizeUrlParameters(username, password)
{
	return "grant_type=password" + 
		   "&scope=" + oauthScopes.join(" ") +
		   "&client_id=" + oauthClientId + 
		   "&client_secret=" + oauthClientSecret + 
		   "&username=" + username + 
		   "&password=" + password;
}

function buildRefreshTokenUrlParameters(refreshToken)
{
	return "grant_type=password" + 
		   "&client_id=" + oauthClientId + 
		   "&client_secret=" + oauthClientSecret + 
		   "&refresh_token=" + refreshToken; 
}