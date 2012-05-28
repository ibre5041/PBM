package org.ibre5041.parsing.visitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.WindowPropertyNode;
import org.ibre5041.parsing.utils.ParseException;
import org.ibre5041.parsing.window.AbstractWindow;
import org.ibre5041.parsing.window.DataWindow;
import org.ibre5041.parsing.window.WindowSubPropertyNode;
import org.ibre5041.parsing.window.util.Column;
import org.ibre5041.parsing.window.util.PropertyTable;
import org.ibre5041.parsing.window.util.TextLabel;

public class PropertyTableVisitor extends BaseVisitor {

	public PropertyTableVisitor(DataWindow ref) {
		this._ref = ref;
	}

	@Override
	public void visit(WindowPropertyNode n) {
		String propName = nodeToString(n.getTree().getChild(0));

		// multi value subpropery "column"
		if (propName.equalsIgnoreCase("column")) {
			Column c = new Column(_ref);
			ColumnVisitor cv = new ColumnVisitor(c);
			AbstractWindow.walk(n.getTree().getChild(1), Arrays.asList((BaseVisitor)cv), 0);
			_ref.addColumn(c);
			
			_PropertyTableStack.push(NULL_TABLE);
			return;
		}
		
		// multi value subpropery "text"
		if (propName.equalsIgnoreCase("text")) {
			TextLabel _lastTextLabel = new TextLabel(_ref);
			_PropertyTableStack.push(_lastTextLabel);
			_ref.addTextLabel(_lastTextLabel);
			return;
		}

		// Okay we've found some window property, is this one listed in the enum
		// PropertyTableType?
		DataWindow.PropertyTableTypeEnum currentProperty = DataWindow.PropertyTableTypeEnum.enumFromName(propName);
		if (currentProperty == null)
		{
			_PropertyTableStack.push(NULL_TABLE);
			return;
		}

		if (_ref.getProperties().containsKey(currentProperty))
			throw new RuntimeException("Parser error: duplicate window property: " + propName);

		_PropertyTableStack.push(new PropertyTable(currentProperty.toString()));
		_ref.getProperties().put(currentProperty, _PropertyTableStack.peek());
	}

	@Override
	public void unvisit(WindowPropertyNode n) {
		_PropertyTableStack.pop();
	}
	
	@Override
	public void visit(WindowSubPropertyNode n) {
		Tree astNode = n.getTree();
		String subPropName = nodeToString(astNode.getChild(0));
		
		if (subPropName.equalsIgnoreCase("column")) {
			_PropertyTableStack.push(NULL_TABLE);
			return;
		}
		
		// Generic properties
		if (_PropertyTableStack.peek() == NULL_TABLE)
			return;
		
//		if (astNode.getChild(1).getChildCount() > 1)
//			throw new ParseException("Parser error: multivalue property: " + subPropName, astNode.getLine(), astNode.getCharPositionInLine());

		String value = nodeToString(astNode.getChild(1));
		_PropertyTableStack.peek().put(subPropName, value);
	}
	
	@Override
	public void unvisit(WindowSubPropertyNode n) {
		Tree astNode = n.getTree();
		String subPropName = nodeToString(astNode.getChild(0));		
		if (subPropName.equalsIgnoreCase("column"))
			_PropertyTableStack.pop();
	}
	
	private DataWindow _ref;
	private Deque<PropertyTable> _PropertyTableStack = new ArrayDeque<PropertyTable>();
	// bacause ArrayDeque can not hold real nulls
	private static PropertyTable NULL_TABLE = new PropertyTable("NULL_TABLE");
}
