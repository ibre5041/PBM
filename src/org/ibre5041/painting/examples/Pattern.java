package org.ibre5041.painting.examples;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPen;
import com.trolltech.qt.gui.QWidget;

/**
 * ZetCode QtJambi tutorial
 *
 * This program draws nine rectangles.
 * The interiors are filled with
 * different built-in patterns.
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */

public class Pattern extends QWidget {
    
    public Pattern() {
        
        setWindowTitle("Patterns");

        resize(350, 280);
        move(400, 300);
        show();
    }

    @Override
    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        drawPatterns(painter);
    }

    private void drawPatterns(QPainter painter) {

          painter.setPen(QPen.NoPen);

          painter.setBrush(Qt.BrushStyle.HorPattern);
          painter.drawRect(10, 15, 90, 60);

          painter.setBrush(Qt.BrushStyle.VerPattern);
          painter.drawRect(130, 15, 90, 60);

          painter.setBrush(Qt.BrushStyle.CrossPattern);
          painter.drawRect(250, 15, 90, 60);

          painter.setBrush(Qt.BrushStyle.Dense7Pattern);
          painter.drawRect(10, 105, 90, 60);

          painter.setBrush(Qt.BrushStyle.Dense6Pattern);
          painter.drawRect(130, 105, 90, 60);

          painter.setBrush(Qt.BrushStyle.Dense5Pattern);
          painter.drawRect(250, 105, 90, 60);

          painter.setBrush(Qt.BrushStyle.BDiagPattern);
          painter.drawRect(10, 195, 90, 60);

          painter.setBrush(Qt.BrushStyle.FDiagPattern);
          painter.drawRect(130, 195, 90, 60);

          painter.setBrush(Qt.BrushStyle.DiagCrossPattern);
          painter.drawRect(250, 195, 90, 60);

          painter.end();
    }
    
    
    public static void main(String[] args) {
        QApplication.initialize(args);
        new Pattern();
        QApplication.execStatic();
    }
}
