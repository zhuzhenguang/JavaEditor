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
	 * RTextAreaEditorKit的undo,并绑定ctrl+z
	 */
	Action undoAction = new RTextAreaEditorKit.UndoAction("撤销上次操作.", null, "无法撤销", new Integer('U'), KeyStroke.getKeyStroke(KeyEvent.VK_Z,
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
