package org.ibre5041.ui;

import java.util.List;
import java.util.Vector;

import org.ibre5041.parsing.window.PBFile;

import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;

public class FileListModel extends QAbstractListModel {
	private List<PBFile> _data = new Vector<PBFile>();

	@Override
	public Object data(QModelIndex index, int role) {

		if (index.row() < 0 || index.row() >= _data.size())
			return null;

		switch (role) {
		case ItemDataRole.UserRole:
			return _data.get(index.row());
		case ItemDataRole.DisplayRole:
			return _data.get(index.row()).getFilename().getName();
		default:
			return null;
		}
	}

	@Override
	public int rowCount(QModelIndex parent) {
		return _data.size();
	}

	public boolean addpendRow(PBFile row) {
		this.beginInsertRows(null, _data.size(), _data.size());
		boolean retval = this.insertRow(_data.size());
		_data.add(row);
		this.endInsertRows();
		return retval;
	}

	public boolean addpendRows(Vector<PBFile> newData) {
		this.beginInsertRows(null, _data.size(), _data.size() + newData.size());
		boolean retval = this.insertRows(_data.size(), newData.size());
		_data.addAll(newData);
		this.endInsertRows();
		return retval;
	}
}
