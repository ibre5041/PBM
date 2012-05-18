package org.ibre5041.parsing.visitor;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.window.DataWindow;
import org.ibre5041.parsing.window.WindowPropertyNode;
import org.ibre5041.parsing.window.WindowSubPropertyNode;
import org.ibre5041.parsing.window.util.PropertyTable;

public class PropertyTableVisitor extends BaseVisitor {

	public PropertyTableVisitor(DataWindow ref) {
		this._ref = ref;
	}

	@Override
	public void visit(WindowPropertyNode n) {
		// Okay we've found some window property, is this one listed in the enum
		// PropertyTableType?
		String propName = n.getTree().getChild(0).getText();
		DataWindow.PropertyTableTypeEnum currentProperty = DataWindow.PropertyTableTypeEnum
				.enumFromName(propName);
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
		String name = astNode.getChild(0).getChild(0).getText();
		String value = astNode.getChild(1).getChild(0).getText();
		_lastPropertyTable.put(name, value);		
	}

	private DataWindow _ref;
	private PropertyTable _lastPropertyTable = null;
}
