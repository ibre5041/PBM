package org.ibre5041.parsing.window;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

public enum WindowTypeEnum {
	//suffixes = Arrays.asList(".sra", ".srd", ".srf", ".srj", ".srm", ".srq", ".srs", ".sru", ".srw");
	APP("sra"),
<<<<<<< HEAD
	DATA("srd"),
	WINOBJ("srw");
=======
	DATA_WINDOW("srd"),
	FUNCTION("srf"),
	PIPELINE("srp"),
	//PROJECT("srj"),
	MENU("srm"),
	QUERY("srq"),
	STRUCT("srs"),
	USER_OBJ("sru"),
	WININDOW("srw");	
>>>>>>> 56b435f28c3f0120dc83f9419bb89779d78f3dc8
	
	public String getSuffix() {
		return _suffix;		
	}
	
	public static Set<String> getSuffixes() {
		return _str2enum.keySet();
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
