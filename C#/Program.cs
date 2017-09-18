using System.Collections.Generic;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// This example demonstrates how we can access the customer endpoint of DQ Express 2 using C#.
    /// </summary>
    class Program
    {
        /// <summary>
        /// The username and the password are used to authenticate and authorize the customer.
        /// </summary>
        private const string DqExpressUserName = "<change me>";
        private const string DqExpressPassword = "<change me>";

        static void Main()
        {
            var apiToken = DqExpressAuthorizationClient.Authorize(DqExpressUserName, DqExpressPassword).Result;

            var customerClient = new DqExpressCustomerClient { AccessToken = apiToken.AccessToken};

            var result = customerClient.ExecuteModule("<module name>", 
                new ExecuteModuleInputData {FieldNames = new List<string>{ "<field names>" }, Record = new List<string>{ "<records>" }}, 
                "<session id (optional)>").Result;
        }
    }
}