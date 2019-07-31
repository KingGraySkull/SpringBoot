package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OOC_ISO_FIELD_MASTER")
public class IsoField {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ooc_iso_field_master_SEQUENCE", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ISO_FIELD_ID")
	private Integer fieldId;
	
	@Column(name = "FIELD_LENGTH")
	private Integer length;
	
	@Column(name = "ISO_FIELD_NAME")
	private String name;
	
	@Column(name = "ISO_FIELD_KEY")
	private String key;
	
	@Column(name = "ISO_FIELD_TYPE")
	private String type;
	
	@Column(name = "ISO_FIELD_CLASS")
	private String fieldClass;

	public IsoField() {}
	
	public IsoField(Integer fieldId, Integer length, String name, String key, String type, String fieldClass) {
		this.fieldId = fieldId;
		this.length = length;
		this.name = name;
		this.key = key;
		this.type = type;
		this.fieldClass = fieldClass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(String fieldClass) {
		this.fieldClass = fieldClass;
	}
}
