package com.javaeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.javaeditor.operation.MenuSet;
import com.javaeditor.toolbar.Toolbar;
import com.javaeditor.util.TabContentUtil;
import com.javaeditor.util.listenter.DocumentChangeListener;

/**
 * The Class MainWindow.
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	/** 主内容的面板. */
	private JPanel mainContent;

	/** 主内容的tab面板. */
	private JTabbedPane tabbedPane;

	/** 记录所有打开的tab页. */
	private Map<Integer, Object> editorMap = new HashMap<Integer, Object>();

	/** 记录tab页内容是新的还是旧的. */
	private Map<Integer, Object> recordMap = new HashMap<Integer, Object>();

	/**
	 * Gets the tabbed pane.
	 * 
	 * @return the tabbed pane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Gets the editor map.
	 * 
	 * @return the editor map
	 */
	public Map<Integer, Object> getEditorMap() {
		return editorMap;
	}

	/**
	 * Gets the record map.
	 * 
	 * @return the record map
	 */
	public Map<Integer, Object> getRecordMap() {
		return recordMap;
	}

	/**
	 * Instantiates a new main window. 构造函数，初始化一个window对象
	 */
	public MainWindow() {
		super("新建文件 - JavaEditor");

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		mainContent = new JPanel();
		mainContent.setBorder(new BevelBorder(BevelBorder.LOWERED));
		mainContent.setBackground(Color.white);
		mainContent.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(mainContent, BorderLayout.CENTER);
		// 添加一个工具栏
		Toolbar toolbar = new Toolbar(this);
		contentPane.add(toolbar.getToolbar(), BorderLayout.NORTH);

		tabbedPane = new JTabbedPane();
		// 添加默认的选项卡内容
		TabContentUtil.setTabbedContent(null, this);
		// 添加文本修改监听器
		setDocumentListenter();

		// 添加选项卡
		mainContent.add(tabbedPane);

		// 添加菜单
		new MenuSet(this);

	}

	/**
	 * Sets the document listener. 设置文本监听器
	 */
	public void setDocumentListenter() {
		RSyntaxTextArea editor = (RSyntaxTextArea) getEditorMap().get(getTabbedPane().getSelectedIndex());
		editor.getDocument().addDocumentListener(new DocumentChangeListener(tabbedPane));
	}

}
