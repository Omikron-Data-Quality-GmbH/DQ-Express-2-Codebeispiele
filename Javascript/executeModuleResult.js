/**
 * Represents the result of an module execution.
 */
class ExecuteModuleResult {
	constructor(data) {
		this.FieldNames = [];
		this.Records = [];
		this.SessionId = "";
		
		Object.assign(this, data);
	}
}