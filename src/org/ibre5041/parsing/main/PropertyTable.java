package org.ibre5041.parsing.main;

import java.util.HashMap;

public class PropertyTable extends HashMap<String, String> {
	private static final long serialVersionUID = 6522402886786349437L;

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
}
