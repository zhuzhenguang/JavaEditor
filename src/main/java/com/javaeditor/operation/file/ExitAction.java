package com.javaeditor.operation.file;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {

	public ExitAction() {
		super("  �˳�  ");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
