using Newtonsoft.Json;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// Represents the api token which is returned by the authorization endpoint.
    /// </summary>
    public class ApiToken
    {
        /// <summary>
        /// The access token is used to authorize a user to access the customer endpoint of DQ Express 2.
        /// </summary>
        [JsonProperty("access_token")]
        public string AccessToken { get; set; }

        /// <summary>
        /// Defines how long a token is valid.
        /// </summary>
        [JsonProperty("expires_in")]
        public int ExpiresIn { get; set; }

        /// <summary>
        /// Defines the token type. The token type should always be "Bearer".
        /// </summary>
        [JsonProperty("token_type")]
        public string TokenType { get; set; }

        /// <summary>
        /// The refresh token is used to refresh the user authorization if the access token is no longer valid.
        /// </summary>
        [JsonProperty("refresh_token")]
        public string RefreshToken { get; set; }
    }
}