package org.ibre5041.painting.examples;
import com.trolltech.qt.core.QPointF;
import com.trolltech.qt.core.QRectF;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QFontMetrics;
import com.trolltech.qt.gui.QLineF;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QWidget;


public class Burning extends QWidget {

    private final int PANEL_HEIGHT = 30;
    private final int DISTANCE = 19;
    private final int LINE_WIDTH = 5;
    private final int DIVISIONS = 10;
    private final float FULL_CAPACITY = 700f;
    private final float MAX_CAPACITY = 750f;
    private final QColor redColor = new QColor(255, 175, 175);
    private final QColor yellowColor = new QColor(255, 255, 184);

    private QWidget parent;
    private String num[] = {
        "75", "150", "225", "300",
        "375", "450", "525", "600",
        "675"
    };


    public Burning(QWidget parent) {
        super(parent);

        this.parent = parent;
        setMinimumHeight(PANEL_HEIGHT);
    }

    @Override
    protected void paintEvent(QPaintEvent event) {

        QPainter painter = new QPainter(this);
        
        drawWidget(painter);
        painter.end();
    }

    protected void drawWidget(QPainter painter) {

        BurningApp burn = (BurningApp) parent;

        float width = size().width();        
        float slid_width = burn.getCurrentWidth();
        float step = width / DIVISIONS;

        float till = (width / MAX_CAPACITY) * slid_width;
        float full = (width / MAX_CAPACITY) * FULL_CAPACITY;

        if (slid_width > FULL_CAPACITY) {

            painter.setPen(yellowColor);
            painter.setBrush(yellowColor);
            painter.drawRect(new QRectF(0, 0, full, PANEL_HEIGHT));
            painter.setPen(redColor);
            painter.setBrush(redColor);
            painter.drawRect(new QRectF(full+1, 0, till-full, PANEL_HEIGHT));

        } else {
            if (slid_width > 0) {
               painter.setPen(yellowColor);
               painter.setBrush(yellowColor);
               painter.drawRect(new QRectF(0, 0, till, PANEL_HEIGHT));
            }
        }

        painter.setPen(new QColor(90, 90, 90));
        painter.setBrush(QBrush.NoBrush);
        painter.drawRect(0, 0, size().width()-1, PANEL_HEIGHT-1);

        QFont newFont = font();
        newFont.setPointSize(7);
        painter.setFont(newFont);
        
        for (int i = 1; i <= num.length; i++) {
            painter.drawLine(new QLineF(i*step, 1, i*step, LINE_WIDTH));

            QFontMetrics metrics = new QFontMetrics(newFont);

            int w = metrics.width(num[i-1]);
            painter.drawText(new QPointF(i*step-w/2, DISTANCE), num[i-1]);

        }        
    }
}
