package org.ibre5041.parsing.main;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

public enum WindowType {
	//suffixes = Arrays.asList(".sra", ".srd", ".srf", ".srj", ".srm", ".srq", ".srs", ".sru", ".srw");
	APP("sra"),
	DATA("srd");
	
	public String getSuffix() {
		return _suffix;		
	}
	
	public static WindowType enumFromSuffix(String suffix) {
		return _str2enum.get(suffix);
	}
	
	private WindowType(String suffix) {
		_suffix = suffix;
	}
		
	private String _suffix;
	private static HashMap<String, WindowType> _str2enum;
	
	static
    {
		_str2enum = new HashMap<String, WindowType>();
        for (WindowType wt : EnumSet.allOf(WindowType.class))
        {
        	_str2enum.put(wt._suffix.toLowerCase(), wt);
        }
    }

	 
}
