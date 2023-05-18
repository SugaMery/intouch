package sn.intouch.gu.api.ejb.utils;

public class FunctionArg{
	private String name;
	private String value;
	private Object expectedValue;
	private String expectedValueCondition;
	private String expectedType;
	
	public FunctionArg(String name, String value, Object expectedValue, String expectedValueCondition, String expectedType) {
		super();
		this.name = name;
		this.value = value;
		this.expectedValue = expectedValue;
		this.expectedValueCondition = expectedValueCondition;
		this.expectedType = expectedType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(Object expectedValue) {
		this.expectedValue = expectedValue;
	}
	
	public String getExpectedValueCondition() {
		return expectedValueCondition;
	}

	public void setExpectedValueCondition(String expectedValueCondition) {
		this.expectedValueCondition = expectedValueCondition;
	}

	public String getExpectedType() {
		return expectedType;
	}

	public void setExpectedType(String expectedType) {
		this.expectedType = expectedType;
	}
	
}
