package org.ibre5041.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.PBMLexer;
import org.ibre5041.parsing.PBMParser;
import org.ibre5041.parsing.utils.ANTLRNoCaseFileStream;
import org.ibre5041.parsing.window.PBFile;
import org.ibre5041.parsing.window.PBFileWidget;
import org.ibre5041.parsing.window.WindowFactory;
import org.ibre5041.util.DirList;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.QThread;
import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QObject;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QFileDialog.FileMode;

public class List1 extends QMainWindow {

	public class Producer extends QSignalEmitter implements Runnable {

		public Signal1<PBFile> newDataReady = new Signal1<PBFile>();
		public Signal1<Vector<PBFile>> newDataVectorReady = new Signal1<Vector<PBFile>>();

		private Vector<PBFile> buffer = new Vector<PBFile>();
		private long lastEmitted = QDateTime.currentMSecsSinceEpoch();

		public Producer(String inputDir) {
			this.m_inputDir = inputDir;
		}

		@Override
		public void run() {
			for (File file : DirList.listDirectory(m_inputDir)) {

				PBFile window = null;
				try {
					window = WindowFactory.getInstance().createWindow(file);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (RecognitionException e) {
					e.printStackTrace();
				}
				if (window != null)
					buffer.add(window);

				long now = QDateTime.currentMSecsSinceEpoch();
				if (now - lastEmitted > 200 && !buffer.isEmpty()) { // push the queue every 200ms
					newDataVectorReady.emit(buffer);
					buffer = new Vector<PBFile>();
					lastEmitted = now;
				}
			}

			if (!buffer.isEmpty()) {
				newDataVectorReady.emit(buffer);
			}
		}

		private String m_inputDir;
	}

	public static void main(String[] args) {
		QApplication.initialize(args);

		List1 testList1 = new List1();
		testList1.show();

		Producer producer = testList1.new Producer(args[0]);
		// producer.newDataReady.connect(testList1, "appendData(String)");
		producer.newDataVectorReady.connect(testList1, "appendDataVector(Vector)");		
		QThread qThread = new QThread(producer);
		qThread.start();

		QApplication.execStatic();
	}

	@SuppressWarnings("unused")
	private void appendData(PBFile newData) {
		FileListModel m = (FileListModel) ui.listView.model();
		m.addpendRow(newData);
	}

	@SuppressWarnings("unused")
	private void appendDataVector(Vector<PBFile> newData) {
		FileListModel m = (FileListModel) ui.listView.model();
		m.addpendRows(newData);
	}

	@SuppressWarnings("unused")
	private void currentChanged(QModelIndex current, QModelIndex old) {		
		QWidget prev = (QWidget)ui.verticalLayoutWidget.findChild(PBFileWidget.class);
		if( prev != null) {
			ui.verticalLayout.removeWidget((QWidget)prev);
			prev.close();
			prev.dispose();
		}
		
		currentFile = (PBFile) fileListModel.data(current, ItemDataRole.UserRole);		
		if(currentFile == null)
			return;
		
		if(ui.right != null)
			ui.right.hide();
				
		PBFileWidget w = new PBFileWidget(ui.verticalLayoutWidget, currentFile);		
		ui.verticalLayout.addWidget(w);
		ui.verticalLayout.update();
	}
	
	public List1() {
		ui.setupUi(this);
		ui.listView.setModel(fileListModel);
		sm = new QItemSelectionModel(fileListModel);
		ui.listView.setSelectionModel(sm);
		sm.currentChanged.connect(this, "currentChanged(QModelIndex, QModelIndex)");
	}

	public List1(QWidget parent) {
		super(parent);
		ui.setupUi(this);
		ui.listView.setModel(fileListModel);
		sm = new QItemSelectionModel(fileListModel);
		ui.listView.setSelectionModel(sm);		
	}

	Ui_List1 ui = new Ui_List1();
	FileListModel fileListModel = new FileListModel();
	QItemSelectionModel sm = null;
	PBFile currentFile = null;
}
