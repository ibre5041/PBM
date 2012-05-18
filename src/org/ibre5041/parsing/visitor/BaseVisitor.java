package org.ibre5041.parsing.visitor;

import org.ibre5041.parsing.window.WindowPropertyNode;
import org.ibre5041.parsing.window.WindowSubPropertyNode;

public abstract class BaseVisitor {
	public abstract void visit(WindowPropertyNode n);
	public abstract void visit(WindowSubPropertyNode n);
}
