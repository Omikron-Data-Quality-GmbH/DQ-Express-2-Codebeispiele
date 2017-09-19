<?php

/**
 * Represents the input data for an module execution.
 */
class ExecuteModuleInputData implements JsonSerializable {
	
    /**
     * The field name array for the provided record.
     */	
	private $fieldNames;
	
	/**
     * The values array for the provided record.
     */
	private $record;
	
	public function setFieldNames($fieldNames) {
		$this->fieldNames = $fieldNames;
	}
	
	public function setRecord($record) {
		$this->record = $record;
	}
	
	public function jsonSerialize() {
		return [
			'fieldNames' => $this->fieldNames,
			'record' => $this->record
		];
	}
}

?>