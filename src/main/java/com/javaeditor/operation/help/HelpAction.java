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
		super("  ����  ");
		this.parent = parent;
	}

	/**
	 * ���ڰ�����ֻ�а汾��Ϣ
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(parent, "<html><b>JavaEditor</b> - һ���򵥵�Java�ı��༭��" + "<br>Version 1.0" + "<br>Author:zhuzhenguang",
				"About JavaEditor", JOptionPane.INFORMATION_MESSAGE);
	}
}
