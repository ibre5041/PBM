package org.ibre5041.painting;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.ibre5041.parsing.window.DataWindow;
import org.ibre5041.parsing.window.PBFile;
import org.ibre5041.parsing.window.WindowFactory;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QWidget;

public class PaintText1 extends QWidget {

    public PaintText1(PBFile window) {
        this._pbfile = window;
        
        setWindowTitle("PaintText1");

        resize(350, 280);
        move(400, 300);
        show();
    }	

    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        //drawPatterns(painter);
        _pbfile.paint(painter);
    }    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	        QApplication.initialize(args);						
			
			PBFile window = WindowFactory.getInstance().createWindow(new File(args[0]));
			if( window instanceof DataWindow)
			{
				System.out.println( ((DataWindow)window).getSQL());
			}
			System.out.println(window.getAST().toStringTree());
			
			new PaintText1(window);
	        QApplication.execStatic();
	        
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
 	}
	
	private PBFile _pbfile;
}
