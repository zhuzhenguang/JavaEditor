package com.javaeditor.operation.file;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.javaeditor.MainWindow;
import com.javaeditor.util.TabContentUtil;
import com.javaeditor.util.listenter.DocumentChangeListener;

@SuppressWarnings("serial")
public class NewAction extends AbstractAction {

	private MainWindow parent;

	public NewAction(MainWindow parent) {
		super("  ÐÂ½¨  ");
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TabContentUtil.setTabbedContent(null, parent);
		RSyntaxTextArea editor = (RSyntaxTextArea) parent.getEditorMap().get(parent.getTabbedPane().getSelectedIndex());
		editor.getDocument().addDocumentListener(new DocumentChangeListener(parent.getTabbedPane()));
	}

}
