import java.util.Arrays;

public class Program 
{

	public static void main(String[] args) 
	{
		try 
		{
			ApiToken apiToken = DqExpressAuthorizationClient.authorize("<username>", "<password>");

			DqExpressCustomerClient client = new DqExpressCustomerClient(apiToken.getAccessToken());
			
			ExecuteModuleInputData inputData = new ExecuteModuleInputData();		
			inputData.setFieldNames(Arrays.asList("<field names>"));
			inputData.setRecord(Arrays.asList("<records>"));
			
			ExecuteModuleResult result = client.executeModule("<module name>", inputData);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}