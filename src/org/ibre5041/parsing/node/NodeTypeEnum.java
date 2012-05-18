package org.ibre5041.parsing.node;

import java.util.EnumSet;
import java.util.HashMap;

import org.ibre5041.parsing.PBMParser;

public enum NodeTypeEnum {
	WINDOWPROP(PBMParser.WINDOWPROP), 
	WINDOWSUBPROP(PBMParser.WINDOWSUBPROP);

	public int getCode() {
		return _code;
	}

	public static NodeTypeEnum fromId(int id) {
		return _lookup.get(id);
	}

	private NodeTypeEnum(int code) {
		_code = code;
	}

	private int _code;
	private static final HashMap<Integer, NodeTypeEnum> _lookup = new HashMap<Integer, NodeTypeEnum>();

	static {
		for (NodeTypeEnum nt : EnumSet.allOf(NodeTypeEnum.class)) {
			_lookup.put(nt.getCode(), nt);
		}
	}
}
