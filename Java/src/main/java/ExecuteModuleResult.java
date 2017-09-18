import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the result of an module execution.
 */
public class ExecuteModuleResult 
{
	private List<String> fieldNames;
	
	private List<List<String>> records;
	
	private String sessionId;

	@JsonProperty("FieldNames")
	public List<String> getFieldNames() 
	{
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) 
	{
		this.fieldNames = fieldNames;
	}

	@JsonProperty("Records")
	public List<List<String>> getRecords() 
	{
		return records;
	}

	public void setRecords(List<List<String>> records) 
	{
		this.records = records;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}
}