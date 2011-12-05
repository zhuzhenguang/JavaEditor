package com.javaeditor.operation.help;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.javaeditor.MainWindow;

/**
 * The Class HelpAction.
 */
@SuppressWarnings("serial")
public class HelpAction extends AbstractAction {

	/** The parent. */
	private MainWindow parent;

	/**
	 * Instantiates a new help action.
	 * 
	 * @param parent the parent
	 */
	public HelpAction(MainWindow parent) {
		super("  关于  ");
		this.parent = parent;
	}

	/**
	 * 现在帮助里只有版本信息
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(parent, "<html><b>JavaEditor</b> - 一个简单的Java文本编辑器" + "<br>Version 1.0" + "<br>Author:zhuzhenguang",
				"About JavaEditor", JOptionPane.INFORMATION_MESSAGE);
	}
}
