package org.ibre5041.parsing.window;

import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QWidget;

public class PBFileWidget extends QWidget {

	public PBFileWidget(QWidget parent, PBFile pbfile) {
		super(parent);
		this._pbfile = pbfile;
	}
	
    public PBFileWidget(PBFile pbfile) {
    	super();
        this._pbfile = pbfile;        
//        resize(350, 280);
//        move(400, 300);
//        show();
    }	

    protected void paintEvent(QPaintEvent event) {
        QPainter painter = new QPainter(this);
        _pbfile.paint(painter);
    }
	
	private PBFile _pbfile;
}
