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
		if (tl.containsValue("name"))
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
		// String retval =
		// _properties.get(PropertyTableTypeEnum.TABLE).get("retrieve");
		// retval = retval.replaceAll("~\"", "\""); // replace PBish ~" by "
		return "";
	}

	@Override
	public void paint(QPainter painter) {		
		QRect v = painter.viewport();
		_width = v.width() - 1;
		
		painter.setPen(QPen.NoPen);
		painter.setBrush(Qt.BrushStyle.NoBrush);		
		painter.setPen(new QColor(GlobalColor.darkBlue));
		paintHeader(painter);
		paintDetail(painter);
		paintSummay(painter);
		paintFooter(painter);
	}
		
	private void paintHeader(QPainter painter) {
		PropertyTable h = getProperty(PropertyTableTypeEnum.HEADER);
		if ( h == null)
			return;
		int height = Integer.parseInt( h.get("height"));
		if(height == 0)
			return;
		painter.drawRect(0, 0, _width, height);
	}
	
	private void paintDetail(QPainter painter) {
		PropertyTable d = getProperty(PropertyTableTypeEnum.DETAIL);
		if ( d == null)
			return;
		int height = Integer.parseInt( d.get("height"));
		if(height == 0)
			return;
		painter.drawRect(0, 0, _width, height);		
	}
	
	private void paintSummay(QPainter painter) {
		PropertyTable s = getProperty(PropertyTableTypeEnum.SUMMARY);
		int height = Integer.parseInt( s.get("height"));
		if(height == 0)
			return;		
		painter.drawRect(0, 0, _width, height);
	}
	
	private void paintFooter(QPainter painter) {
		PropertyTable f = getProperty(PropertyTableTypeEnum.FOOTER);
		int height = Integer.parseInt( f.get("height"));
		if(height == 0)
			return;		
		painter.drawRect(0, 0, _width, height);		
	}
	
	private int _width;
}
