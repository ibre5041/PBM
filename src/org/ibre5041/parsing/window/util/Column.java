package org.ibre5041.parsing.window.util;

import org.ibre5041.parsing.window.DataWindow;

public class Column extends PropertyTable {
	private static final long serialVersionUID = -6217298457223305416L;
	
	public Column(DataWindow parent) {
		super(""); // Column's name is not known yet
		this._parent = parent;
	}	
	
    public String put(String key, String value) {
    	String retval = super.put(key, value);
    	if (key.equalsIgnoreCase("name")) {
    		_parent.addColumn(this);
    	}
    	
    	return retval;
    }
	
	private DataWindow _parent;
}
