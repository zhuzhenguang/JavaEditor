package com.javaeditor.operation.file;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.javaeditor.MainWindow;
import com.javaeditor.io.FileSaver;
import com.javaeditor.util.CommonUtil;

/**
 * The Class SaveAction.
 */
@SuppressWarnings("serial")
public class SaveAction extends AbstractAction {

	/** The parent. */
	private MainWindow parent;

	/**
	 * Instantiates a new save action.初始化
	 * 
	 * @param parent the parent
	 */
	public SaveAction(MainWindow parent) {
		super("  保存  ");
		this.parent = parent;
	}

	/**
	 * 选择路径并保存
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<Integer, Object> recordMap = parent.getRecordMap();
		int selectedIndex = parent.getTabbedPane().getSelectedIndex();
		Object record = recordMap.get(selectedIndex);
		JTabbedPane tabbedPane = parent.getTabbedPane();
		if (record.equals("new")) {
			File saveNew = this.saveNew();
			if (saveNew != null) {
				tabbedPane.setTitleAt(selectedIndex, saveNew.getName());
				parent.setTitle(saveNew.getName() + " - JavaEditor");
				recordMap.put(selectedIndex, saveNew);
			}
		} else {
			this.saveOld((File) record);
			String title = CommonUtil.removeFlag(tabbedPane.getTitleAt(selectedIndex));
			tabbedPane.setTitleAt(selectedIndex, title);
			parent.setTitle(title + " - JavaEditor");
		}
	}

	/**
	 * Save new. 保存一个新文本
	 * 
	 * @return the file
	 */
	private File saveNew() {
		JFileChooser chooser = new JFileChooser();
		int state = chooser.showSaveDialog(parent);

		if (state != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		File f = chooser.getSelectedFile();
		Thread saver = new FileSaver(f, getSelectedEditor().getDocument(), parent);
		saver.start();

		return f;
	}

	/**
	 * Save old.保存已有文本
	 * 
	 * @param record the record
	 */
	private void saveOld(File record) {
		Thread saver = new FileSaver((File) record, getSelectedEditor().getDocument(), parent);
		saver.start();
	}

	/**
	 * Gets the selected editor.获得文本区域
	 * 
	 * @return the selected editor
	 */
	private JTextArea getSelectedEditor() {
		Map<Integer, Object> editorMap = parent.getEditorMap();
		JTabbedPane tabbedPane = parent.getTabbedPane();
		int selectedIndex = tabbedPane.getSelectedIndex();
		return (JTextArea) editorMap.get(selectedIndex);
	}

}
