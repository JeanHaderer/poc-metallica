package fr.insee.metallica.workflow.command.processor;

import com.fasterxml.jackson.databind.JsonNode;

class SubWorkflowContext {
	private String workflowName;
	private JsonNode context;
	
	public JsonNode getContext() {
		return context;
	}
	public void setContext(JsonNode context) {
		this.context = context;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
}