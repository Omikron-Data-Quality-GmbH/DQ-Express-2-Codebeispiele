using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// This client is used to authorize a customer to the endpoint of DQ Express 2.
    /// </summary>
    public class DqExpressAuthorizationClient
    {
        /// <summary>
        /// The oauth client id and the secret are used to authenticate the client.
        /// Only trusted clients should be able to call the customer endpoint.
        /// Please keep in mind, that you never publish the client id and secret to untrusted sources.
        /// </summary>
        private const string OAuthClientId = "<change me>";
        private const string OAuthClientSecret = "<change me>";

        /// <summary>
        /// The oauth scopes are defining which resources the user can access.
        /// </summary>
        private static readonly string[] OAuthScopes =
        {
            // With this scope, an user is able to access the customer service endpoint.
            "Omikron.DataQuality.Express.CustomerService.Api",
            // With this scope, an expired token can be refreshed. 
            // An access token expires in 20 minutes. If the token is expired, we can`t access the customer endpoint with the same access token anymore.
            // You have to request an new access token using the refresh token.
            "offline_access"
        };

        /// <summary>
        /// The authorization endpoint which is used to authorize the user and retrieve an api access token.
        /// </summary>
        private const string AuthorizationServiceUrl = "https://<change me>/connect/token";

        /// <summary>
        /// Authorize the user and returns an new access token.
        /// </summary>
        public static async Task<ApiToken> Authorize(string username, string password)
        {
            var httpClient = new HttpClient();

            var fromUrlEncodedContent = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("grant_type", "password"),
                new KeyValuePair<string, string>("scope", string.Join(" ", OAuthScopes)),
                new KeyValuePair<string, string>("client_id", OAuthClientId),
                new KeyValuePair<string, string>("client_secret", OAuthClientSecret),
                new KeyValuePair<string, string>("username", username),
                new KeyValuePair<string, string>("password", password),
            });

            var response = await httpClient.PostAsync(AuthorizationServiceUrl, fromUrlEncodedContent);

            if (response.StatusCode != HttpStatusCode.OK)
            {
                throw new Exception("Something went wrong... Handle your error.");
            }

            var jsonApiToken = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<ApiToken>(jsonApiToken);
        }

        /// <summary>
        /// Refresh the authorization token using a valid refresh token.
        /// </summary>
        public static async Task<ApiToken> RefreshAuthorization(string refreshToken)
        {
            var httpClient = new HttpClient();

            var fromUrlEncodedContent = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("grant_type", "refresh_token"),
                new KeyValuePair<string, string>("client_id", OAuthClientId),
                new KeyValuePair<string, string>("client_secret", OAuthClientSecret),
                new KeyValuePair<string, string>("refresh_token", refreshToken),
            });

            var response = await httpClient.PostAsync(AuthorizationServiceUrl, fromUrlEncodedContent);

            if (response.StatusCode != HttpStatusCode.OK)
            {
                throw new Exception("Something went wrong... Handle your error.");
            }

            var jsonApiToken = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<ApiToken>(jsonApiToken);
        }
    }
}