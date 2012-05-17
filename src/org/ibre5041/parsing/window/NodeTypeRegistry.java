package org.ibre5041.parsing.window;

import java.util.HashMap;

public class NodeTypeRegistry extends HashMap<NodeTypeEnum, BaseNode> {
	private static final long serialVersionUID = -7127993798811687771L;

	private static final NodeTypeRegistry _instance;

	public static NodeTypeRegistry getInstance() {
		return _instance;
	}

	static {
		_instance = new NodeTypeRegistry();
		// - an instance of the class is created,
		// - a static method of the class is invoked,
		// - a static field of the class is assigned,
		// - a non-constant static field is used, or
		// - for a top-level class, an assert statement lexically nested within
		// the class is executed.
		WindowPropertyNode n1 = new WindowPropertyNode();
		NodeTypeRegistry.getInstance().put(n1.getNodetype(), n1);
		WindowSubPropertyNode n2 = new WindowSubPropertyNode();
		NodeTypeRegistry.getInstance().put(n2.getNodetype(), n2);
	}
}
