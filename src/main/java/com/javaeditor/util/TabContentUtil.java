package com.javaeditor.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.javaeditor.MainWindow;
import com.javaeditor.util.listenter.TabChangeListener;

/**
 * The Class TabContentUtil.
 */
public class TabContentUtil {

	/**
	 * Sets the tabbed content.初始化一个文本区域,此方法为多个操作提供服务,如初始化、新建、打开
	 * 
	 * @param tabTitle the tab title
	 * @param parent the parent
	 * 
	 * @return the j text area
	 */
	public static JTextArea setTabbedContent(String tabTitle, MainWindow parent) {
		// init
		JTabbedPane tabbedPane = parent.getTabbedPane();
		Map<Integer, Object> editorMap = parent.getEditorMap();
		Map<Integer, Object> recordMap = parent.getRecordMap();

		RSyntaxTextArea editor = new RSyntaxTextArea();
		editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		RTextScrollPane sp = new RTextScrollPane(editor);
		sp.setPreferredSize(new Dimension(770, 470));

		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(770, 480));
		content.add(sp, BorderLayout.CENTER);

		if (tabTitle == null) {
			tabbedPane.addTab("新建文件", new ImageIcon(CommonUtil.getResource("sunImage")), content);
		} else {
			tabbedPane.addTab(tabTitle, new ImageIcon(CommonUtil.getResource("sunImage")), content);
		}

		tabbedPane.setSelectedComponent(content);
		editorMap.put(tabbedPane.getSelectedIndex(), editor);
		recordMap.put(tabbedPane.getSelectedIndex(), "new");
		tabbedPane.addChangeListener(new TabChangeListener(parent));

		return editor;
	}
}
