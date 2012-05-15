package org.ibre5041.painting.examples;

import com.trolltech.qt.core.QPoint;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QWidget;

/**
 * ZetCode QtJambi tutorial
 *
 * This program draws text
 * on the window
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */

public class Text extends QWidget {
    
    public Text() {
        
        setWindowTitle("Soulmate");

        resize(370, 240);
        move(400, 300);
        show();
    }

    @Override
    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        drawLyrics(painter);

    }

    private void drawLyrics(QPainter painter) {

        painter.setBrush(new QColor(25, 25, 25));
        painter.setFont(new QFont("Purisa", 10));

        painter.drawText(new QPoint(20, 30),
                "Most relationships seem so transitory");
        painter.drawText(new QPoint(20, 60),
                "They're good but not the permanent one");
        painter.drawText(new QPoint(20, 120),
                "Who doesn't long for someone to hold");
        painter.drawText(new QPoint(20, 150),
                "Who knows how to love without being told");
        painter.drawText(new QPoint(20, 180),
                "Somebody tell me why I'm on my own");
        painter.drawText(new QPoint(20, 210),
                "If there's a soulmate for everyone");
    }
    
    
    public static void main(String[] args) {
        QApplication.initialize(args);
        new Text();
        QApplication.execStatic();
    }
}
