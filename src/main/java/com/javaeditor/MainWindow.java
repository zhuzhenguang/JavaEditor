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

	/** �����ݵ����. */
	private JPanel mainContent;

	/** �����ݵ�tab���. */
	private JTabbedPane tabbedPane;

	/** ��¼���д򿪵�tabҳ. */
	private Map<Integer, Object> editorMap = new HashMap<Integer, Object>();

	/** ��¼tabҳ�������µĻ��Ǿɵ�. */
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
	 * Instantiates a new main window. ���캯������ʼ��һ��window����
	 */
	public MainWindow() {
		super("�½��ļ� - JavaEditor");

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		mainContent = new JPanel();
		mainContent.setBorder(new BevelBorder(BevelBorder.LOWERED));
		mainContent.setBackground(Color.white);
		mainContent.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(mainContent, BorderLayout.CENTER);
		// ���һ��������
		Toolbar toolbar = new Toolbar(this);
		contentPane.add(toolbar.getToolbar(), BorderLayout.NORTH);

		tabbedPane = new JTabbedPane();
		// ���Ĭ�ϵ�ѡ�����
		TabContentUtil.setTabbedContent(null, this);
		// ����ı��޸ļ�����
		setDocumentListenter();

		// ���ѡ�
		mainContent.add(tabbedPane);

		// ��Ӳ˵�
		new MenuSet(this);

	}

	/**
	 * Sets the document listener. �����ı�������
	 */
	public void setDocumentListenter() {
		RSyntaxTextArea editor = (RSyntaxTextArea) getEditorMap().get(getTabbedPane().getSelectedIndex());
		editor.getDocument().addDocumentListener(new DocumentChangeListener(tabbedPane));
	}

}
