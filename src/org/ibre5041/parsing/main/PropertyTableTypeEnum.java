package org.ibre5041.parsing.main;

import java.util.EnumSet;
import java.util.HashMap;

public enum PropertyTableTypeEnum {
	DATAWINDOW("datawindow"), 
	HEADER("header"), 
	SUMMARY("summary"),
	FOOTER("footer"),
	DETAIL("detail"),
	TABLE("table");

	public String getName() {
		return _name;
	}
	private PropertyTableTypeEnum(String name) {
		_name = name;
	}

	public static PropertyTableTypeEnum enumFromName(String name) {
		return _str2enum.get(name);
	}

	private String _name;
	private static HashMap<String, PropertyTableTypeEnum> _str2enum;
	
	static
    {
		_str2enum = new HashMap<String, PropertyTableTypeEnum>();
        for (PropertyTableTypeEnum wt : EnumSet.allOf(PropertyTableTypeEnum.class))
        {
        	_str2enum.put(wt._name.toLowerCase(), wt);
        }
    }	
}
