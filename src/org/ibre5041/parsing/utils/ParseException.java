package org.ibre5041.parsing.utils;

public class ParseException extends RuntimeException {
	
	private static final long serialVersionUID = -5579273305424301619L;

	public ParseException(String message) {
		super(message);
	}	

	public ParseException(String message, int line, int pos) {
		super(message);
		this._line = line;
		this._pos = pos;
	}	

	public String toString() {
		StringBuffer sb = new StringBuffer(64);
		sb.append('[').append(_line).append(',').append(_pos).append(']');
		sb.append(super.toString());
		return sb.toString();
	}
	
	public void setPosition(int line, int pos) {
		this._line = line;
		this._pos = pos;
	}
	
	private int _line, _pos;	
}
