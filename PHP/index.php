<html>
	<body>
		<?php 

			require_once('apiToken.php');
			require_once('dqExpressAuthorizationClient.php');
			require_once('dqExpressCustomerClient.php');
			require_once('executeModuleInputData.php');
			require_once('executeModuleResult.php');

			const USERNAME = "<change me>";
			const PASSWORD = "<change me>";

			$api_token = DqExpressAuthorizationClient::authorize(USERNAME, PASSWORD);
			$access_token = $api_token->getAccessToken();

			$customerClient = new DqExpressCustomerClient($access_token);

			$inputData = new ExecuteModuleInputData();
			$inputData->setFieldNames(array("<field names>"));
			$inputData->setRecord(array("<record>"));

			$result = $customerClient->executeModule("<module name>", $inputData, "<session id (optional>");

			var_dump($result);

		?>
	</body>
</html>