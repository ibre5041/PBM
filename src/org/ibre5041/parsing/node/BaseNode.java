package org.ibre5041.parsing.node;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.visitor.BaseVisitor;

public abstract class BaseNode implements Cloneable {
	public abstract void accept(BaseVisitor v);
	
	
	protected BaseNode(Tree astNode)
	{
		init(astNode);
	}
	
	protected BaseNode()
	{
		this._astNode =  null;		
	}
	
	// Hey just implement this: this._astNode = astNode;
	protected abstract void init(Tree astNode);
	
	public Tree getTree()
	{
		return _astNode;
	}
	
	protected Tree _astNode; 
}
