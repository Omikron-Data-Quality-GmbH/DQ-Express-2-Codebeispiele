using System.Collections.Generic;
using Newtonsoft.Json;

namespace DqExpressCustomerClientExample
{
    /// <summary>
    /// Represents the input data for an module execution.
    /// </summary>
    public class ExecuteModuleInputData
    {
        /// <summary>
        /// The field name array for the provided record.
        /// </summary>
        [JsonProperty("fieldNames")]
        public List<string> FieldNames { get; set; }

        /// <summary>
        /// The values array for the provided record.
        /// </summary>
        [JsonProperty("record")]
        public List<string> Record { get; set; }
    }
}