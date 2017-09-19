import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This client is used to execute a workflow module
 */
public class DqExpressCustomerClient 
{
	private final String CUSTOMER_SERVICE_MODULE_EXECUTION_URL = "https://<change me>/api/v1/modules/";
	
    /**
     * Gets or sets the access token which is used for authorization.
     */
	private String accessToken;
	
	public DqExpressCustomerClient(String accessToken) 
	{
		this.accessToken = accessToken;
	}
	
	/** Execute a workflow module.
	 * @param moduleName The name of the module to execute
	 * @param inputData The module data structure which should be passed to the workflow module
	 * @return Returns the result of the module execution.
	 */
	public ExecuteModuleResult executeModule(String moduleName, ExecuteModuleInputData inputData) throws Exception
	{
		return executeModule(moduleName, inputData, null);
	}
	
    /** Execute a workflow module.
     * @param moduleName The name of the module to execute
     * @param inputData The module data structure which should be passed to the workflow module
     * @param sessionId The optional session id. We can get a session id by executing a module. 
     * The session id is used to execute similar modules. For example if you call an address suggesst five times with the same address, you only have to pay once.</param>
     * @return Returns the result of the module execution.
     */
	public ExecuteModuleResult executeModule(String moduleName, ExecuteModuleInputData inputData, String sessionId) throws Exception
	{    	
		String urlTemplate = CUSTOMER_SERVICE_MODULE_EXECUTION_URL + moduleName; 
		
		if(sessionId != null && !sessionId.equals(""))
		{
			urlTemplate += String.format("?sessionId=%s", sessionId);
		}
		
    	URL url = new URL(urlTemplate);
    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    	
    	connection.setDoOutput(true);
    	connection.setRequestMethod("PUT");
    	connection.setRequestProperty("Content-Type", "application/json");
    	connection.setRequestProperty("Authorization", "Bearer " + accessToken);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.writeValue(connection.getOutputStream(), inputData);

    	int statusCode = connection.getResponseCode();
    	
    	if(statusCode != 200) 
    	{
    		throw new Exception("Something went wrong... Handle your error.");
    	}
    
    	return mapper.readValue(connection.getInputStream(), ExecuteModuleResult.class);
	}
}
