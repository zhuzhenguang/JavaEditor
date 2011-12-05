package com.javaeditor.operation.edit;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import org.fife.ui.rtextarea.RTextAreaEditorKit;

/**
 * The Class RedoAction.
 */
@SuppressWarnings("serial")
public class RedoAction extends AbstractAction {

	/**
	 * The redo action.
	 * 
	 * RTextAreaEditorKit的redo,并绑定ctrl+y
	 */
	Action redoAction = new RTextAreaEditorKit.RedoAction("执行下个操作.", null, "无法重复", new Integer('R'), KeyStroke.getKeyStroke(KeyEvent.VK_Y,
			InputEvent.CTRL_MASK));

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		redoAction.actionPerformed(e);
	}
}
