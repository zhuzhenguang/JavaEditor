package com.javaeditor.toolbar;

import java.awt.Insets;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.javaeditor.MainWindow;
import com.javaeditor.util.CommonUtil;

/**
 * The Class Toolbar.
 */
public class Toolbar {

	/**
	 * Instantiates a new toolbar.初始化一个toolbar
	 * 
	 * @param parent the parent
	 */
	public Toolbar(MainWindow parent) {
		toolbar = new JToolBar();
		String[] toolKeys = CommonUtil.getToolkeys();
		Map<String, Action> actions = CommonUtil.getAllAction(parent);

		// 根据资源文件里的内容，初始化图标和action
		for (String toolKey : toolKeys) {
			JButton shortcut = new JButton(new ImageIcon(CommonUtil.getResource(toolKey)));
			shortcut.setRequestFocusEnabled(false);
			shortcut.setMargin(new Insets(1, 1, 1, 1));
			shortcut.setToolTipText(CommonUtil.getResourceString(toolKey + "-tooltip"));
			shortcut.addActionListener(actions.get(toolKey));
			toolbar.add(shortcut);
			if (toolKey.equals("saveImage") || toolKey.equals("cutImage")) {
				toolbar.addSeparator();
			}
		}
	}

	/** The toolbar. */
	private JToolBar toolbar;

	/**
	 * Gets the toolbar.
	 * 
	 * @return the toolbar
	 */
	public JToolBar getToolbar() {
		return toolbar;
	}

}
