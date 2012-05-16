package org.ibre5041.parsing.main;

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
	private PropertyTableType(String suffix) {
		_suffix = suffix;
	}

	private String _suffix;
}
