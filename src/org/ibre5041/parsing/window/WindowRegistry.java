package org.ibre5041.parsing.window;

import java.util.HashMap;


public class WindowRegistry extends HashMap<String, Window> 
{	
	private static final long serialVersionUID = -7127993798811687771L;
	
	private static final WindowRegistry instance = new WindowRegistry();

	public static WindowRegistry getInstance() {
		return instance;
	}
}
