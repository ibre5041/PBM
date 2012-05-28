package org.ibre5041.parsing.window;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.visitor.BaseVisitor;
import org.ibre5041.parsing.visitor.PropertyTableVisitor;
import org.ibre5041.parsing.window.util.PropertyTable;
import org.ibre5041.parsing.window.util.TextLabel;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.GlobalColor;	
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPen;

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
			for (PropertyTableTypeEnum wt : EnumSet.allOf(PropertyTableTypeEnum.class)) {
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

	// Single occurence properties
	public HashMap<PropertyTableTypeEnum, PropertyTable> getProperties() {
		return _properties;
	}
	
	public PropertyTable getProperty(PropertyTableTypeEnum e) {
		return _properties.get(e);
	}
	
	public boolean hasProperty(PropertyTableTypeEnum e) {
		return _properties.containsKey(e);
	}

	public void setProperties(HashMap<PropertyTableTypeEnum, PropertyTable> _properties) {
		this._properties = _properties;
	}

	private HashMap<PropertyTableTypeEnum, PropertyTable> _properties = new HashMap<PropertyTableTypeEnum, PropertyTable>();

	// Multiple occurence properties
	public List<PropertyTable> getTexts() {
		return _ltexts;
	}

	public PropertyTable getText(String name) {
		return _mtexts.get(name);
	}

	public void setTexts(List<PropertyTable> _texts) {
		this._ltexts = _texts;
	}

	private List<PropertyTable> _ltexts = new LinkedList<PropertyTable>();
	private Map<String, PropertyTable> _mtexts = new HashMap<String, PropertyTable>();

	public void addTextLabel(TextLabel tl) {
		if (!_ltexts.contains(tl))
			_ltexts.add(tl);
		if (!tl.containsValue("name"))
			_mtexts.put(tl.get("name"), tl);
	}
	
//	public HashMap<String, PropertyTable> getColumns() {
//		return _columns;
//	}
//
//	public PropertyTable getColumn(String key) {
//		return _columns.get(key);
//	}
//
//	public void addColumn(PropertyTable column) {
//		this._columns.put(column.get("name"), column);
//	}
//
//	private HashMap<String, PropertyTable> _columns = new HashMap<String, PropertyTable>();

	public String toString() {
		StringBuffer sb = new StringBuffer(128);
		for (PropertyTableTypeEnum key: _properties.keySet()) {
			sb.append(key).append(':').append("\n");
			sb.append(_properties.get(key)).append("\n");
		}
		for (String textName: _mtexts.keySet()) {
			sb.append("text.").append(textName).append(":\n");
			sb.append(_mtexts.get(textName)).append("\n");
		}
		return sb.toString();
	}
	
	public String getSQL() {
		String retval =	_properties.get(PropertyTableTypeEnum.TABLE).getUnescaped("retrieve");
		if (retval == null)
			return "";
		retval = retval.replaceAll("~\"", "\""); // replace PBish ~" by "
		return retval;
	}

	@Override
	public void paint(QPainter painter) {
		_y = 0;
		QRect v = painter.viewport();
		_width = v.width() - 1;
		
		painter.setPen(QPen.NoPen);
		painter.setBrush(Qt.BrushStyle.NoBrush);		
		painter.setPen(new QColor(GlobalColor.darkBlue));
	
		paintPropertyTable(painter, PropertyTableTypeEnum.HEADER);
		painter.drawText( painter.viewport().translated(2, _y + 40), Qt.TextFlag.TextWordWrap.value(), getSQL());
		paintPropertyTable(painter, PropertyTableTypeEnum.DETAIL);
		paintPropertyTable(painter, PropertyTableTypeEnum.SUMMARY);
		paintPropertyTable(painter, PropertyTableTypeEnum.FOOTER);		
	}
		
	private void paintPropertyTable(QPainter painter, PropertyTableTypeEnum e)
	{
		PropertyTable f = getProperty(e);
		if (f == null)
			return;
		int height = Integer.parseInt( f.get("height"));
		if (height == 0)
			return;		
		painter.drawRect(0, _y + 0, _width, _y + height);
		painter.drawText(2, _y + 20, e.toString());
		paintTexts(painter, e.getName());
		_y += height + 2;		
	}
	
	private void paintTexts(QPainter painter, String band)
	{
		for (PropertyTable tl : _ltexts) {
			if( !tl.get("band").equalsIgnoreCase(band))
				continue;
			int x = Integer.parseInt( tl.getUnescaped("x"));					
			int y = Integer.parseInt( tl.getUnescaped("y"));
			painter.drawText(x, _y + y, tl.getUnescaped("text"));
		}
	}
	
	private int _width, _y;
}
