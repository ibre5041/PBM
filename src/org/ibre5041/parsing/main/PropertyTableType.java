package org.ibre5041.parsing.main;

import java.util.EnumSet;
import java.util.HashMap;

public enum PropertyTableType {
	DATAWINDOW("datawindow"), 
	HEADER("header"), 
	SUMMARY("summary"),
	FOOTER("footer"),
	DETAIL("detail"),
	TABLE("table");

	public String getSuffix() {
		return _suffix;
	}

	public static PropertyTableType enumFromSuffix(String suffix) {
		return _str2enum.get(suffix);
	}

	private PropertyTableType(String suffix) {
		_suffix = suffix;
	}

	private String _suffix;
	private static HashMap<String, PropertyTableType> _str2enum;

	static {
		_str2enum = new HashMap<String, PropertyTableType>();
		for (PropertyTableType wt : EnumSet.allOf(PropertyTableType.class)) {
			_str2enum.put(wt._suffix.toLowerCase(), wt);
		}
	}
}
