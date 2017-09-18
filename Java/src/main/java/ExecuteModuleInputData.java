import java.util.List;

/**
 * Represents the input data for an module execution.
 */
public class ExecuteModuleInputData 
{
    /**
     * The field name array for the provided record.
     */
	private List<String> fieldNames;
	
    /**
     * The values array for the provided record.
     */
	private List<String> record;

	public List<String> getFieldNames()
	{
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames)
	{
		this.fieldNames = fieldNames;
	}

	public List<String> getRecord()
	{
		return record;
	}

	public void setRecord(List<String> record) 
	{
		this.record = record;
	}
}