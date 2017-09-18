import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents the api token which is returned by the authorization endpoint.
 */
@JsonIgnoreProperties({"id_token"})
public class ApiToken 
{
    /**
     * The access token is used to authorize a user to access the customer endpoint of DQ Express 2.
     */
	private String accessToken;
	
    /**
     * Defines how long a token is valid.
     */
	private int expiresIn;
	
	/**
	 * Defines the token type. The token type should always be "Bearer".
	 */
	private String tokenType;
	
    /**
     * The refresh token is used to refresh the user authorization if the access token is no longer valid.
     */
	private String refreshToken;
	
	@JsonProperty("access_token")
	public String getAccessToken() 
	{
		return accessToken;
	}
	
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	
	@JsonProperty("expires_in")
	public int getExpiresIn() 
	{
		return expiresIn;
	}
	
	public void setExpiresIn(int expiresIn) 
	{
		this.expiresIn = expiresIn;
	}
	
	@JsonProperty("token_type")
	public String getTokenType() 
	{
		return tokenType;
	}
	
	public void setTokenType(String tokenType) 
	{
		this.tokenType = tokenType;
	}
	
	@JsonProperty("refresh_token")
	public String getRefreshToken() 
	{
		return refreshToken;
	}
	
	public void setRefreshToken(String refreshToken) 
	{
		this.refreshToken = refreshToken;
	}
}