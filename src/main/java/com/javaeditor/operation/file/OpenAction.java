package com.javaeditor.operation.file;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import com.javaeditor.MainWindow;
import com.javaeditor.io.FileLoader;
import com.javaeditor.util.TabContentUtil;

/**
 * The Class OpenAction.
 */
@SuppressWarnings("serial")
public class OpenAction extends AbstractAction {

	/** The parent. */
	private MainWindow parent;

	/** The chooser. 选择器*/
	private JFileChooser chooser;

	/**
	 * Instantiates a new open action.初始化
	 * 
	 * @param parent the parent
	 */
	public OpenAction(MainWindow parent) {
		super("  打开  ");
		this.parent = parent;
		this.chooser = new JFileChooser();
		filter();
	}

	/**
	 * 选择一个文本并执行FileLoader线程
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int state = chooser.showOpenDialog(parent);
		if (chooser.getSelectedFile() == null || state != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File selectedFile = chooser.getSelectedFile();
		JTextArea newEditor = TabContentUtil.setTabbedContent(selectedFile.getName(), parent);
		FileLoader fileLoader = new FileLoader(parent, newEditor, selectedFile.getParent(), selectedFile.getName());
		fileLoader.start();

		Map<Integer, Object> recordMap = parent.getRecordMap();
		recordMap.put(parent.getTabbedPane().getSelectedIndex(), selectedFile);
	}

	/**
	 * 过滤条件.
	 */
	private void filter() {
		chooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				String fileExt = FileLoader.getFileNameExt(f.getPath()).toLowerCase();
				if (f.isDirectory() || fileExt.equalsIgnoreCase(".java") || fileExt.equalsIgnoreCase(".txt") || fileExt.equalsIgnoreCase(".gif")) {
					return true;
				}
				return false;
			}

			public String getDescription() {
				return "Java源代码与text文本（*.java;*.txt）";
			}
		});
		chooser.setAcceptAllFileFilterUsed(false);
	}

}
