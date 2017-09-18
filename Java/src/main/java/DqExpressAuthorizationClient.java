import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This client is used to authorize a customer to the endpoint of DQ Express 2.
 */
public class DqExpressAuthorizationClient 
{
	/**
	 * The oauth client id and the secret are used to authenticate the client.
	 * Only trusted clients should be able to call the customer endpoint.
	 * Please keep in mind, that you never publish the client id and secret to untrusted sources.
	 */
	private static final String OAUTH_CLIENT_ID = "<change me>";
	private static final String OAUTH_CLIENT_SECRET = "<change me>";
	
    /**
     * The oauth scopes are defining which resources the user can access.
     */
	private static final String[] OAUTH_SCOPES = 
	{
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
	};
	
    /**
     * The authorization endpoint which is used to authorize the user and retrieve an api access token.
     */
    private static final String AUTHORIZATION_SERVICE_URL = "https://<change me>/connect/token";
    
    /**
     * Authorize the user and returns an new access token.
     */
    public static ApiToken authorize(String username, String password) throws Exception 
    {	
    	String urlParameters = buildAuthorizeUrlParameters(username, password);  	
    	
    	URL url = new URL(AUTHORIZATION_SERVICE_URL);
    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    	
    	connection.setDoOutput(true);
    	connection.setRequestMethod("POST");
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	
    	DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
    	outputStream.writeBytes(urlParameters);
    	outputStream.flush();
    	outputStream.close();
    	
    	int statusCode = connection.getResponseCode();
    	
    	if(statusCode != 200) 
    	{
    		throw new Exception("Something went wrong... Handle your error.");
    	}
    
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.readValue(connection.getInputStream(), ApiToken.class);
    }
   
    /**
     * Refresh the authorization token using a valid refresh token.
     */
    public static ApiToken refreshAuthorization(String refreshToken) throws Exception
    {
    	String urlParameters = buildRefreshTokenUrlParameters(refreshToken);  	
    	
    	URL url = new URL(AUTHORIZATION_SERVICE_URL);
    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    	
    	connection.setDoOutput(true);
    	connection.setRequestMethod("POST");
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	
    	DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
    	outputStream.writeBytes(urlParameters);
    	outputStream.flush();
    	outputStream.close();
    	
    	int statusCode = connection.getResponseCode();
    	
    	if(statusCode != 200) 
    	{
    		throw new Exception("Something went wrong... Handle your error.");
    	}
    
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.readValue(connection.getInputStream(), ApiToken.class);
    }
    
    private static String buildAuthorizeUrlParameters(String username, String password)
    {
    	return String.format("grant_type=password&scope=%s&client_id=%s&client_secret=%s&username=%s&password=%s", 
    			String.join(" ", OAUTH_SCOPES),
    			OAUTH_CLIENT_ID,
    			OAUTH_CLIENT_SECRET,
    			username,
    			password);
    }
    
    private static String buildRefreshTokenUrlParameters(String refreshToken)
    {
    	return String.format("grant_type=refresh_token&client_id=%s&client_secret=%s&refresh_token=%s", 
    			OAUTH_CLIENT_ID,
    			OAUTH_CLIENT_SECRET,
    			refreshToken);
    }
}
