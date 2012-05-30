package org.ibre5041.parsing.window.util;

import org.ibre5041.parsing.window.DataWindow;

public class TextLabel extends PropertyTable {
	private static final long serialVersionUID = -8703858319526346845L;
	
	public TextLabel(DataWindow parent) {
		super(""); // text label name is not known yet
		this._parent = parent;
	}

    public String put(String key, String value) {
    	String retval = super.put(key, value);
    	if (key.equalsIgnoreCase("name")) {
    		_parent.addTextLabel(this);
    	}
    	
    	return retval;
    }
	private DataWindow _parent;
}
