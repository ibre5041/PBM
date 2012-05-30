package org.ibre5041.ui;

import com.trolltech.qt.gui.*;

public class List1 extends QMainWindow {

    Ui_List1 ui = new Ui_List1();

    public static void main(String[] args) {
        QApplication.initialize(args);

        List1 testList1 = new List1();
        testList1.show();

        QApplication.execStatic();
    }

    public List1() {
        ui.setupUi(this);
    }

    public List1(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
