package utils;

public enum ItemsOption {
	SELL("sell"),
	BUY("buy");
	
	private String value;
	
	private ItemsOption(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

