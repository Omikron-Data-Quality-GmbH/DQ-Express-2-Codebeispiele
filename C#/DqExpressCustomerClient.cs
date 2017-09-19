using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// This client is used to execute a workflow module
    /// </summary>
    public class DqExpressCustomerClient
    {
        private const string CustomerServiceModuleExecutionUrl = "https://<change me>/api/v1/modules";
        private const string JsonMediaType = "application/json";
        private const string SessionIdHeaderName = "SessionId";

        /// <summary>
        /// Gets or sets the access token which is used for authorization.
        /// </summary>
        public string AccessToken { get; set; }

        /// <summary>
        /// Execute a workflow module.
        /// </summary>
        /// <param name="moduleName">The name of the module to execute.</param>
        /// <param name="inputData">The module data structure which should be passed to the workflow module.</param>
        /// <param name="sessionId">The optional session id. We can get a session id by executing a module. 
        /// The session id is used to execute similar modules. For example if you call an address suggesst five times with the same address, you only have to pay once.</param>
        /// <returns>Returns the result of the module execution.</returns>
        public async Task<ExecuteModuleResult> ExecuteModule(string moduleName,  ExecuteModuleInputData inputData, string sessionId = null)
        {
            var httpClient = new HttpClient();

            httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", AccessToken);

            var url = $"{CustomerServiceModuleExecutionUrl}/{moduleName}";
            if (!string.IsNullOrEmpty(sessionId))
            {
                url += $"?sessionId={sessionId}";
            }

            var stringContent = new StringContent(JsonConvert.SerializeObject(inputData), Encoding.UTF8, JsonMediaType);

            var response = await httpClient.PutAsync(url, stringContent);

            if (response.StatusCode != HttpStatusCode.OK)
            {
                throw new Exception("Something went wrong... Handle your error.");
            }

            var jsonResult = await response.Content.ReadAsStringAsync();
            var result = JsonConvert.DeserializeObject<ExecuteModuleResult>(jsonResult);

            var sessionHeader = response.Headers.FirstOrDefault(header => header.Key.Equals(SessionIdHeaderName, StringComparison.OrdinalIgnoreCase)).Value?.FirstOrDefault();
            if (sessionHeader != null)
            {
                result.SessionId = sessionHeader;
            }

            return result;
        }
    }
}