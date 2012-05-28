package org.ibre5041.parsing.window.util;

import java.util.HashMap;

public class PropertyTable extends HashMap<String, String> {
	private static final long serialVersionUID = 6522402886786349437L;

	public PropertyTable(String propName) {
		this._name = propName;
	}

    public String put(String key, String value) {
    	if (this.containsKey(key))
    		throw new RuntimeException("Duplicate key:" + key);
    	return super.put(key, value);
    }
    
    public String getUnescaped(String key) {
    	String retval = get(key);
    		if( retval.charAt(0) == '\"')
    			return retval.substring(1, retval.length()-1);
    	return retval;
    }
    
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer(128);
		b.append("[").append(this.getClass().getName()).append(" ");
		 
		for (java.util.Map.Entry<String, String> entry : this.entrySet()) {
		       b.append(entry.getKey());
		       b.append("=");
		       b.append(entry.getValue());
		       b.append(" ");
		   }
		b.append("]");
		return b.toString();
	}		
	String _name;
}
