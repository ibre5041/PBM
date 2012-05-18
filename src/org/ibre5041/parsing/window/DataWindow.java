package org.ibre5041.parsing.window;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.BaseNode;
import org.ibre5041.parsing.node.NodeTypeEnum;
import org.ibre5041.parsing.node.NodeTypeRegistry;
import org.ibre5041.parsing.visitor.BaseVisitor;
import org.ibre5041.parsing.visitor.PropertyTableVisitor;
import org.ibre5041.parsing.window.util.PropertyTable;

public class DataWindow extends AbstractWindow implements Window {

	public enum PropertyTableTypeEnum {
		DATAWINDOW("datawindow"), 
		HEADER("header"), 
		SUMMARY("summary"), 
		FOOTER("footer"),
		DETAIL("detail"),
		TABLE("table");

		public String getName() {
			return _name;
		}

		private PropertyTableTypeEnum(String name) {
			_name = name;
		}

		public static PropertyTableTypeEnum enumFromName(String name) {
			return _str2enum.get(name);
		}

		private String _name;
		private static HashMap<String, PropertyTableTypeEnum> _str2enum;

		static {
			_str2enum = new HashMap<String, PropertyTableTypeEnum>();
			for (PropertyTableTypeEnum wt : EnumSet
					.allOf(PropertyTableTypeEnum.class)) {
				_str2enum.put(wt._name.toLowerCase(), wt);
			}
		}
	}

	@Override
	public void setAST(Tree ast) {
		super.setAST(ast);
		// Visitors this window cares about
		List<BaseVisitor> visitors = new LinkedList<BaseVisitor>();
		BaseVisitor v = new PropertyTableVisitor(this);
		visitors.add(v);
		walk(ast, visitors);
	}
	
	public HashMap<PropertyTableTypeEnum, PropertyTable> getProperties() {
		return _properties;
	}
	
	public void setProperties(HashMap<PropertyTableTypeEnum, PropertyTable> _properties) {
		this._properties = _properties;
	}
	// Single occurence properties
	private HashMap<PropertyTableTypeEnum, PropertyTable> _properties = new HashMap<PropertyTableTypeEnum, PropertyTable>();
	
	// Multiple occurence properties
	public List<PropertyTable> getTexts() {
		return _texts;
	}

	public void setTexts(List<PropertyTable> _texts) {
		this._texts = _texts;
	}
	
	private List<PropertyTable> _texts = new LinkedList<PropertyTable>();
	
	public String getSQL()
	{
//		String retval = _properties.get(PropertyTableTypeEnum.TABLE).get("retrieve");
//		retval  = retval.replaceAll("~\"", "\""); // replace PBish ~" by "  
		return "";
	}
}
