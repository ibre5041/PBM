package org.ibre5041.painting.examples;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QFrame;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QSlider;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

/**
 * ZetCode QtJambi tutorial
 *
 * In this program, we create
 * a custom widget
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */

public class BurningApp extends QFrame {

    private final int MAX_CAPACITY = 750;

    QSlider slider;
    QWidget widget;
    int cur_width;

    public BurningApp() {
        setWindowTitle("The Burning Widget");

        initUI();

        resize(370, 200);
        move(300, 300);
        show();
    }

    private void initUI() {
       
       slider = new QSlider(Qt.Orientation.Horizontal , this);
       slider.setMaximum(MAX_CAPACITY);
       slider.setGeometry(50, 50, 130, 30);

       slider.valueChanged.connect(this, "valueChanged(int)");

       QVBoxLayout vbox = new QVBoxLayout(this);
       QHBoxLayout hbox = new QHBoxLayout();

       vbox.addStretch(1);

       widget = new Burning(this);
       hbox.addWidget(widget, 0);

       vbox.addLayout(hbox);

       setLayout(vbox);
    }

    public void valueChanged(int val) {
        cur_width = val;
        widget.repaint();
    }

    public int getCurrentWidth() {
      return cur_width;
    }


    public static void main(String[] args) {
        QApplication.initialize(args);
        new BurningApp();
        QApplication.execStatic();
    }
}
