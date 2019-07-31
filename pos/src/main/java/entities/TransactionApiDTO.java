package entities;

public class TransactionApiDTO {
	
	private Integer id;
	private Integer serviceApiId;
	private String serviceApiName;
	private Integer isoFieldId;
	private String isoFieldName;
	private String isoFieldKey;
	private Integer isoFieldLength;
	private String isoFieldType;
	private String padvalue;
	private String padDirection;
	private String required;
	private String defaultValue;
	
	public TransactionApiDTO() {}

	public TransactionApiDTO(Integer id, Integer serviceApiId, String serviceApiName, Integer isoFieldId,
			String isoFieldName, String isoFieldKey, Integer isoFieldLength, String isoFieldType) {
		this.id = id;
		this.serviceApiId = serviceApiId;
		this.serviceApiName = serviceApiName;
		this.isoFieldId = isoFieldId;
		this.isoFieldName = isoFieldName;
		this.isoFieldKey = isoFieldKey;
		this.isoFieldLength = isoFieldLength;
		this.isoFieldType = isoFieldType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceApiId() {
		return serviceApiId;
	}

	public void setServiceApiId(Integer serviceApiId) {
		this.serviceApiId = serviceApiId;
	}

	public String getServiceApiName() {
		return serviceApiName;
	}

	public void setServiceApiName(String serviceApiName) {
		this.serviceApiName = serviceApiName;
	}

	public Integer getIsoFieldId() {
		return isoFieldId;
	}

	public void setIsoFieldId(Integer isoFieldId) {
		this.isoFieldId = isoFieldId;
	}

	public String getIsoFieldName() {
		return isoFieldName;
	}

	public void setIsoFieldName(String isoFieldName) {
		this.isoFieldName = isoFieldName;
	}

	public String getIsoFieldKey() {
		return isoFieldKey;
	}

	public void setIsoFieldKey(String isoFieldKey) {
		this.isoFieldKey = isoFieldKey;
	}

	public Integer getIsoFieldLength() {
		return isoFieldLength;
	}

	public void setIsoFieldLength(Integer isoFieldLength) {
		this.isoFieldLength = isoFieldLength;
	}

	public String getIsoFieldType() {
		return isoFieldType;
	}

	public void setIsoFieldType(String isoFieldType) {
		this.isoFieldType = isoFieldType;
	}

	public String getPadvalue() {
		return padvalue;
	}

	public void setPadvalue(String padvalue) {
		this.padvalue = padvalue;
	}

	public String getPadDirection() {
		return padDirection;
	}

	public void setPadDirection(String padDirection) {
		this.padDirection = padDirection;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}	
}
