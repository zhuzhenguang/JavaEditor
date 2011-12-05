package com.javaeditor.util.listenter;

import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The listener interface for receiving documentChange events. The class that is
 * interested in processing a documentChange event implements this interface,
 * and the object created with that class is registered with a component using
 * the component's <code>addDocumentChangeListener<code> method. When
 * the documentChange event occurs, that object's appropriate
 * method is invoked.
 * 
 * 此监听器监听文本改动,在标题处加入"*"
 * 
 * @see DocumentChangeEvent
 */
public class DocumentChangeListener implements DocumentListener {

	/** The tabbed pane. */
	private JTabbedPane tabbedPane;

	/**
	 * Instantiates a new document change listener.
	 * 
	 * @param tabbedPane the tabbed pane
	 */
	public DocumentChangeListener(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		documentChangeHandler();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		documentChangeHandler();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		documentChangeHandler();
	}

	/**
	 * Document change handler.
	 */
	private void documentChangeHandler() {
		String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		if (!title.endsWith("*")) {
			tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), title + "*");
		}
	}

}
