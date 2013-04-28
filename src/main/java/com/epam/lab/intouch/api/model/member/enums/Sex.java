package main.java.com.epam.lab.intouch.api.model.member.enums;

public enum Sex {
	MALE, FEMALE;
	
	public static Sex fromString(String string) {
		if (string != null) {
			for (Sex sex : values()) {
				if (string.equalsIgnoreCase(sex.toString())) {
					return sex;
				}
			}
		}
		return null;
	}
}
