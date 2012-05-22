package org.ibre5041.parsing.window.util;

import org.ibre5041.parsing.window.DataWindow;
import org.ibre5041.parsing.window.DataWindow.PropertyTableTypeEnum;

public class TextLabel extends PropertyTable {
	private static final long serialVersionUID = -8703858319526346845L;
	
	public TextLabel(DataWindow parent) {
		super(""); // text label name is not known yet
		this._parent = parent;
	}

    public String put(String key, String value) {
    	if (key.equalsIgnoreCase("name")) {
    		_parent.addTextLabel(this);
    	}
    	
    	return super.put(key, value);
    }

	private int _x, _y, _w, _h;
	private PropertyTableTypeEnum _band;	
	private String _text, _name;
	private String _font;
	private DataWindow _parent;
}
