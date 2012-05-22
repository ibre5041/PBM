package org.ibre5041.parsing.visitor;

import java.util.ArrayList;
import java.util.Arrays;
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
		_lastPropertyTable = null; // reset property table
		String propName = nodeToString(n.getTree());
		// Handle special case property table is the most importannt and has
		// multivalue suppropery "column"
		if (propName.equalsIgnoreCase("table")) {
			return;
		}
//		if (propName.equalsIgnoreCase("column")) {
//			_lastPropertyTable = new PropertyTable(propName.toLowerCase());
//
//			_lastColumn = new Column();
//			ColumnVisitor cv = new ColumnVisitor(_lastColumn);
//			AbstractWindow.walk(n.getTree(), Arrays.asList((BaseVisitor)cv), 0);
//			return;
//		}
		if (propName.equalsIgnoreCase("text")) {
			_lastTextLabel = new TextLabel(_ref);
			_lastPropertyTable = _lastPropertyTable;
			_ref.addTextLabel(_lastTextLabel);
			return;
		}

		// Okay we've found some window property, is this one listed in the enum
		// PropertyTableType?
		DataWindow.PropertyTableTypeEnum currentProperty = DataWindow.PropertyTableTypeEnum.enumFromName(propName);
		if (currentProperty == null)
			return;

		if (_ref.getProperties().containsKey(currentProperty))
			throw new RuntimeException("Parser error: duplicate window property: " + propName);

		_lastPropertyTable = new PropertyTable(currentProperty.toString());
		_ref.getProperties().put(currentProperty, _lastPropertyTable);
	}

	@Override
	public void visit(WindowSubPropertyNode n) {
		Tree astNode = n.getTree();
		String subPropName = nodeToString(astNode.getChild(0));
		
		if (subPropName.equalsIgnoreCase("column")) {
			_lastPropertyTable = new PropertyTable(subPropName.toLowerCase());
			return;
		}
		
		// Generic properties
		if (_lastPropertyTable == null)
			return;
		
//		if (astNode.getChild(1).getChildCount() > 1)
//			throw new ParseException("Parser error: multivalue property: " + subPropName, astNode.getLine(), astNode.getCharPositionInLine());

		String value = nodeToString(astNode.getChild(1));
		_lastPropertyTable.put(subPropName, value);
	}

	private DataWindow _ref;
	private PropertyTable _lastPropertyTable = null;
	private TextLabel _lastTextLabel;
	private Column _lastColumn;
}
