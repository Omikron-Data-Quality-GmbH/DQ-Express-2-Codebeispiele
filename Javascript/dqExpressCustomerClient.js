const customerServiceModuleExecutionUrl = "https://<change me>/api/v1/modules/";

/**
 * Gets or sets the access token which is used for authorization.
 */
var accessToken = "";

/** Execute a workflow module.
* @param accessToken The access token to access the customer api.
* @param moduleName The name of the module to execute
* @param inputData The module data structure which should be passed to the workflow module
* @param sessionId The optional session id. We can get a session id by executing a module. 
* The session id is used to execute similar modules. For example if you call an address suggesst five times with the same address, you only have to pay once.</param>
* @param onSuccessCallback This callback will be called if the module executed successfully.
* @return Returns the result of the module execution.
*/
function executeModule(accessToken, moduleName, inputData, sessionId, onSuccessCallback) {
	let urlTemplate = customerServiceModuleExecutionUrl + moduleName;
	
	if(sessionId) {
		urlTemplate += "?sessionId=" + sessionId;
	}
	
	let http = new XMLHttpRequest();
	http.open("PUT", urlTemplate, true);
	http.setRequestHeader("Content-Type", "application/json");
	http.setRequestHeader("Authorization", "Bearer " + accessToken);
	
	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			let json = JSON.parse(http.responseText);
			onSuccessCallback(new ExecuteModuleResult(json));
		} else if(http.readyState == 4 && http.status != 200) {
			console.log("Something went wrong... Handle your error.");
		}
	}
	
	http.send(JSON.stringify(inputData));
}