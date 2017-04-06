package ah.petrolmanagement.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BaseDto implements Serializable {
	private static final long serialVersionUID = 7209760273222456270L;

	private Integer status;
	private String resultCode;
	private List<String> messages;
	private List<String> errors;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	public void setMessageList(final String[] messages) {
		List<String> messageList = new ArrayList<String>();

		for (String msg : messages) {
			messageList.add(msg);
		}
		this.setMessages(messageList);
	}

	public void setErrorsList(final String[] errors) {
		List<String> errorsList = new ArrayList<String>();

		for (String error : errors) {
			errorsList.add(error);
		}
		this.setErrors(errorsList);
	}
}