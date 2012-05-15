package org.ibre5041.painting.examples;

import com.trolltech.qt.core.QPoint;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPen;
import com.trolltech.qt.gui.QWidget;

/**
 * ZetCode QtJambi tutorial
 *
 * This program draws a donut
 * shape
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */


public class Donut extends QWidget {
    
    public Donut() {
        
        setWindowTitle("Donut");

        resize(350, 280);
        move(400, 300);
        show();
    }

    @Override
    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        drawDonut(painter);

    }

    private void drawDonut(QPainter painter) {

        QColor color = new QColor();
        color.setNamedColor("#333333");

        painter.setPen(new QPen(color, 0.5));

        painter.setRenderHint(QPainter.RenderHint.Antialiasing);

        int w = width();
        int h = height();

        painter.translate(new QPoint(w/2, h/2));

         for (double rot=0; rot < 360.0; rot+=5.0 ) {
             painter.drawEllipse(-125, -40, 250, 80);
             painter.rotate(5.0);
         }
    }

    
    public static void main(String[] args) {
        QApplication.initialize(args);
        new Donut();
        QApplication.execStatic();
    }
}
