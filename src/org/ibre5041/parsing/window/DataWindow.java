package org.ibre5041.parsing.window;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.visitor.BaseVisitor;
import org.ibre5041.parsing.visitor.PropertyTableVisitor;
import org.ibre5041.parsing.window.util.Column;
import org.ibre5041.parsing.window.util.PropertyTable;
import org.ibre5041.parsing.window.util.TextLabel;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.GlobalColor;	
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFontMetrics;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPen;

public class DataWindow extends AbstractWindow implements PBFile {

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

	public enum DimesionUnitType {
		PB(0),
		PIXEL(1),
		INCH(2),
		CM(3);
		
		DimesionUnitType(int value) {
			this._value = value;
		}		
		private int _value;
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

	public List<PropertyTable> getColumns() {
		return _lcolumns;
	}

	public PropertyTable getColumn(String name) {
		return _mcolumns.get(name);
	}

	public void setColumns(List<PropertyTable> _columns) {
		this._lcolumns= _columns;
	}

	private List<PropertyTable> _lcolumns = new LinkedList<PropertyTable>();
	private Map<String, PropertyTable> _mcolumns = new HashMap<String, PropertyTable>();
	
	public void addColumn(Column column) {
		if (!_lcolumns.contains(column))
			_lcolumns.add(column);
		if (!column.containsValue("name"))
			_mcolumns.put(column.get("name"), column);
	}

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
		_y = 1;
		QRect v = painter.viewport();
		_width = v.width() - 1;
		
		painter.setPen(QPen.NoPen);
		painter.setBrush(Qt.BrushStyle.NoBrush);		
		painter.setPen(new QColor(GlobalColor.darkBlue));
	
		paintPropertyTable(painter, PropertyTableTypeEnum.HEADER);
		paintPropertyTable(painter, PropertyTableTypeEnum.DETAIL);
		paintPropertyTable(painter, PropertyTableTypeEnum.SUMMARY);
		paintPropertyTable(painter, PropertyTableTypeEnum.FOOTER);
		paintColumns(painter, "detail");
		_y += 10;
		painter.drawText( painter.viewport().translated(2, _y + 40), Qt.TextFlag.TextWordWrap.value(), getSQL());		
	}
		
	private void paintPropertyTable(QPainter painter, PropertyTableTypeEnum e)
	{
		PropertyTable f = getProperty(e);
		if (f == null)
			return;
		int height = (int) ((int)Integer.parseInt( f.get("height")) * _div_y);
		if (height == 0)
			return;		
		painter.drawRect(0, _y + 0, _width, height);
		painter.drawText(2, _y + 20, e.toString());
		paintTexts(painter, e.getName());
		_y += height + 4;		
	}

	private void paintTexts(QPainter painter, String band)
	{
		for (PropertyTable tl : _ltexts) {
			if( !tl.get("band").equalsIgnoreCase(band))
				continue;
			int x = (int) (Integer.parseInt( tl.getUnescaped("x")) * _div_x);					
			int y = (int) (Integer.parseInt( tl.getUnescaped("y")) * _div_y);
			int textHeight = Integer.parseInt( tl.getUnescaped("font.height"));
			painter.drawText(x, _y + y - textHeight, tl.getUnescaped("text"));
		}
	}

	private void paintColumns(QPainter painter, String band)
	{
		for (PropertyTable tl : _lcolumns) {
			if( !tl.get("band").equalsIgnoreCase(band))
				continue;
			int x = (int) (Integer.parseInt( tl.getUnescaped("x")) * _div_x);					
			int y = (int) (Integer.parseInt( tl.getUnescaped("y")) * _div_y);
			int textHeight = Integer.parseInt( tl.getUnescaped("font.height"));
			painter.drawText(x, _y + y - textHeight, tl.getUnescaped("name"));
		}
	}
	
	private int _width, _y;
	private static float _div_x, _div_y;
	
	//
	// All window measurements are in PowerBuilder units. 
	// Using these units, you can build applications that look similar on different resolution screens. 
	// A horizontal PowerBuilder unit is 1/32 of the width of an average character in the system font. 
	// A vertical PowerBuilder unit is 1/64 of the system font height. 
	static {
		QFontMetrics m = QApplication.fontMetrics();
		_div_x = (float)m.width("x") / 32;
		_div_y = (float) m.height() / 64;
	}
}
