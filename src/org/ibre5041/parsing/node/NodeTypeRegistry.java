package org.ibre5041.parsing.node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.window.WindowPropertyNode;
import org.ibre5041.parsing.window.WindowSubPropertyNode;

public class NodeTypeRegistry extends HashMap<NodeTypeEnum, Class<? extends BaseNode>> {
	private static final long serialVersionUID = -7127993798811687771L;

	private static final NodeTypeRegistry _instance;

	public static NodeTypeRegistry getInstance() {
		return _instance;
	}

    public boolean isRegistered(NodeTypeEnum e)
    {
    	return this.containsKey(e);
    }
    
    public boolean isRegistered(int type)
    {
    	NodeTypeEnum e = NodeTypeEnum.fromId(type);
    	if(e == null)
    		return false;
    	return isRegistered(e);
    }
    
	public BaseNode create(Tree astNode) {
		try {
			NodeTypeEnum e = NodeTypeEnum.fromId(astNode.getType());
			Method method = get(e).getMethod("create", Tree.class);
			BaseNode n = (BaseNode) method.invoke(null, astNode);
			return n;
		} catch (SecurityException e) {
			throw new RuntimeException("No create method for: " + e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No create method for: " + e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("No create method for: " + e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("No create method for: " + e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("No create method for: " + e);
		}
	}
	
	static {
		_instance = new NodeTypeRegistry();
		_instance.put(WindowPropertyNode.TYPE, WindowPropertyNode.class);
		_instance.put(WindowSubPropertyNode.TYPE, WindowSubPropertyNode.class);		
	}
}
