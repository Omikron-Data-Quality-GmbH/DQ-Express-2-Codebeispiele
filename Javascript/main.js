const username = "<change me>";
const password = "<change me>";

authorize(username, password, onAuthorizationSuccess);

/**
 * WARNING: It is very easy to read out the access token via javascript.
 * Anyone who owns the access token can also access the api and book transactions.
 * It is hightly recommend that the authorization process is handled only in trusted sources (e.g. backends).
 */
function onAuthorizationSuccess(apiToken) {
	console.log(apiToken);
	
	let inputData = new ExecuteModuleInputData();
	inputData.fieldnames = ["<field names>"];
	inputData.record = ["<record>"];
	
	executeModule(apiToken.access_token, "<module name>", inputData, "<session id (can be empty)>", onModuleExecutionSuccess);
}

function onModuleExecutionSuccess(executeModuleResult) {
	console.log(executeModuleResult);
}