package org.ibre5041.parsing.window;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

public enum WindowTypeEnum {
	//suffixes = Arrays.asList(".sra", ".srd", ".srf", ".srj", ".srm", ".srq", ".srs", ".sru", ".srw");
	APP("sra"),
	DATA("srd");
	
	public String getSuffix() {
		return _suffix;		
	}
	
	public static WindowTypeEnum enumFromSuffix(String suffix) {
		return _str2enum.get(suffix);
	}
	
	private WindowTypeEnum(String suffix) {
		_suffix = suffix;
	}
		
	private String _suffix;
	private static HashMap<String, WindowTypeEnum> _str2enum;
	
	static
    {
		_str2enum = new HashMap<String, WindowTypeEnum>();
        for (WindowTypeEnum wt : EnumSet.allOf(WindowTypeEnum.class))
        {
        	_str2enum.put(wt._suffix.toLowerCase(), wt);
        }
    }

	 
}
