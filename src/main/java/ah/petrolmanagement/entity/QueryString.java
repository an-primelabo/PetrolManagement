package ah.petrolmanagement.entity;

import java.io.Serializable;

public class QueryString implements Serializable {
	private static final long serialVersionUID = 114204955648057777L;

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}