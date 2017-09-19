<?php

/**
 * Represents the result of an module execution.
 */
class ExecuteModuleResult {
	
	private $fieldNames;
	private $records;
	private $sessionId;
	
	function __construct($json, $session_id) {
		$this->fieldNames = $json->FieldNames;
		$this->records = $json->Records;
		$this->sessionId = $session_id;
	}
	
	public function getFieldNames() {
		return $this->fieldNames;
	}
	
	public function getRecords() {
		return $this->records;
	}
	
	public function getSessionId() {
		return $this->sessionId;
	}
}

?>