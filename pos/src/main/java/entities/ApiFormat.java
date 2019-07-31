package entities;

import java.util.List;

public class ApiFormat {
	
	private String apiName;
	private List<CustomField> customFields;
	
	public ApiFormat() {}
	
	public ApiFormat(String apiName, List<CustomField> customFields) {
		this.apiName = apiName;
		this.customFields = customFields;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}
}
