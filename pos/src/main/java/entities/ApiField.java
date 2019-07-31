package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OCC_API_FORMAT_JSON")
public class ApiField {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "occ_api_format_json_SEQUENCE", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "API_FORMAT_JSON_REQUIRED")
	private String required;
	
	@Column(name = "API_FORMAT_JSON_DEFAULT")
	private String defaultValue;
	
	@Column(name = "PAD_VALUE")
	private String padValue;
	
	@Column(name = "PAD_DIRECTION")
	private String padDirection;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OOC_ISO_FIELD_MASTER_ID", nullable = false)
	private IsoField isoField;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ISO_API_ID", nullable = false)
	private ServiceApi serviceApi;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceApi getServiceApis() {
		return serviceApi;
	}

	public void setServiceApis(ServiceApi serviceApis) {
		this.serviceApi = serviceApis;
	}

	public IsoField getIsoField() {
		return isoField;
	}

	public void setIsoField(IsoField isoField) {
		this.isoField = isoField;
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

	public String getPadValue() {
		return padValue;
	}

	public void setPadValue(String padValue) {
		this.padValue = padValue;
	}

	public String getPadDirection() {
		return padDirection;
	}

	public void setPadDirection(String padDirection) {
		this.padDirection = padDirection;
	}
}
