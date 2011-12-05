package com.javaeditor.operation;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.DefaultEditorKit.CopyAction;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.text.DefaultEditorKit.PasteAction;

import com.javaeditor.MainWindow;
import com.javaeditor.operation.edit.RedoAction;
import com.javaeditor.operation.edit.UndoAction;
import com.javaeditor.operation.file.ExitAction;
import com.javaeditor.operation.file.NewAction;
import com.javaeditor.operation.file.OpenAction;
import com.javaeditor.operation.file.SaveAction;
import com.javaeditor.operation.help.HelpAction;

/**
 * The Class MenuSet.
 */
public class MenuSet {

	/** The parent.������ */
	private MainWindow parent;

	/**
	 * Instantiates a new menu set.
	 * 
	 * @param parent the parent
	 */
	public MenuSet(MainWindow parent) {
		this.parent = parent;
		JMenuBar jMenuBar = new JMenuBar();
		parent.setJMenuBar(jMenuBar);

		JMenu fileMenu = new JMenu("�ļ�");
		JMenu editMenu = new JMenu("�༭");
		JMenu helpMenu = new JMenu("����");
		jMenuBar.add(fileMenu);
		jMenuBar.add(editMenu);
		jMenuBar.add(helpMenu);

		this.createFileMenu(fileMenu);
		this.createEditMenu(editMenu);
		this.createHelpMenu(helpMenu);
	}

	/**
	 * Creates the file menu.�����ļ��˵���
	 * 
	 * @param fileMenu the file menu
	 * 
	 * @return the j menu
	 */
	private JMenu createFileMenu(JMenu fileMenu) {
		Action newAction = new NewAction(parent);
		Action openAction = new OpenAction(parent);
		Action saveAction = new SaveAction(parent);
		Action exitAction = new ExitAction();

		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(exitAction);

		return fileMenu;

	}

	/**
	 * Creates the edit menu.�����༭�˵���
	 * 
	 * @param editMenu the edit menu
	 * 
	 * @return the j menu
	 */
	private JMenu createEditMenu(JMenu editMenu) {
		JMenuItem copy = new JMenuItem("  ����  ");
		copy.addActionListener(new CopyAction());
		JMenuItem paste = new JMenuItem("  ճ��  ");
		paste.addActionListener(new PasteAction());
		JMenuItem cut = new JMenuItem("  ����  ");
		cut.addActionListener(new CutAction());
		JMenuItem redo = new JMenuItem("  �ظ�  ");
		Action redoAction = new RedoAction();
		redo.addActionListener(redoAction);
		JMenuItem undo = new JMenuItem("  ����  ");
		Action undoAction = new UndoAction();
		undo.addActionListener(undoAction);

		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(cut);
		editMenu.add(redo);
		editMenu.add(undo);

		return editMenu;
	}

	/**
	 * Creates the help menu.���������˵���
	 * 
	 * @param helpMenu the help menu
	 * 
	 * @return the j menu
	 */
	private JMenu createHelpMenu(JMenu helpMenu) {
		helpMenu.add(new HelpAction(parent));
		return helpMenu;
	}
}
