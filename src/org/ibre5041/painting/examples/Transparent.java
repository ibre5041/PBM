package org.ibre5041.painting.examples;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPen;
import com.trolltech.qt.gui.QWidget;

/**
 * ZetCode QtJambi tutorial
 *
 * This program draws ten
 * rectangles with different
 * levels of transparency
 * 
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */

public class Transparent extends QWidget {
    
    public Transparent() {
        
        setWindowTitle("Transparent rectangles");

        resize(590, 90);
        move(400, 300);
        show();
    }

    @Override
    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        drawRectangles(painter);

    }

    private void drawRectangles(QPainter painter) {

        painter.setPen(QPen.NoPen);

        for (int i=1; i<11; i++) {
            painter.setBrush(new QColor(0, 0, 255, i*25));
            painter.drawRect(50*i, 20, 40, 40);
        }
    }
    
    
    public static void main(String[] args) {
        QApplication.initialize(args);
        new BurningApp();
        QApplication.execStatic();
    }
}
