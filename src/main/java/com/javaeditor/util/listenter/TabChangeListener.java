package com.javaeditor.util.listenter;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.javaeditor.MainWindow;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving tabChange events. The class that is
 * interested in processing a tabChange event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addTabChangeListener<code> method. When
 * the tabChange event occurs, that object's appropriate
 * method is invoked.
 * 此监听器监听tab切换,改变标题
 * 
 * @see TabChangeEvent
 */
public class TabChangeListener implements ChangeListener {

	/** The parent. */
	private MainWindow parent;

	/**
	 * Instantiates a new tab change listener.
	 * 
	 * @param parent the parent
	 */
	public TabChangeListener(MainWindow parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent
	 * )
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabbedPane = parent.getTabbedPane();
		if (tabbedPane.getSelectedIndex() >= 0) {
			String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
			parent.setTitle(title + " - JavaEditor");
		}
	}

}
