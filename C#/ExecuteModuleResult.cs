using System.Collections.Generic;
using Newtonsoft.Json;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// Represents the result of an module execution.
    /// </summary>
    public class ExecuteModuleResult
    {
        [JsonProperty("FieldNames")]
        public List<string> FieldNames { get; set; }

        [JsonProperty("Records")]
        public List<List<string>> Records { get; set; }

        public string SessionId { get; set; }
    }
}