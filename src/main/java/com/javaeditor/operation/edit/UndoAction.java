package com.javaeditor.operation.edit;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import org.fife.ui.rtextarea.RTextAreaEditorKit;

/**
 * The Class UndoAction.
 */
@SuppressWarnings("serial")
public class UndoAction extends AbstractAction {

	/**
	 * The undo action.
	 * 
	 * RTextAreaEditorKit��undo,����ctrl+z
	 */
	Action undoAction = new RTextAreaEditorKit.UndoAction("�����ϴβ���.", null, "�޷�����", new Integer('U'), KeyStroke.getKeyStroke(KeyEvent.VK_Z,
			InputEvent.CTRL_MASK));

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		undoAction.actionPerformed(e);
	}
}
