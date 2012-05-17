package org.ibre5041.parsing.window;

public abstract class BaseVisitor {
	abstract void visit(WindowPropertyNode n);
	abstract void visit(WindowSubPropertyNode n);
}
