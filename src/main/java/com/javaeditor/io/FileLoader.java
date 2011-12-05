package com.javaeditor.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

import com.javaeditor.MainWindow;
import com.javaeditor.util.listenter.DocumentChangeListener;

/**
 * The Class FileLoader.
 */
public class FileLoader extends Thread {

	/** The parent. 主窗口 */
	private MainWindow parent;

	/** The textarea. 文本区 */
	private JTextArea textarea;

	/** The directory. 文本文件夹 */
	private String directory;

	/** The filename. 文本名称 */
	private String filename;

	/**
	 * Instantiates a new file loader. 初始化
	 * 
	 * @param parent the parent
	 * @param textarea the textarea
	 * @param directory the directory
	 * @param filename the filename
	 */
	public FileLoader(MainWindow parent, JTextArea textarea, String directory, String filename) {
		this.parent = parent;
		this.textarea = textarea;
		this.directory = directory;
		this.filename = filename;
	}

	/**
	 * 单独启动一个线程来加载文本
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if ((filename == null) || (filename.length() == 0))
			return;
		File f;
		FileReader in = null;

		// 读取展示内容，因为读取的是纯文本，因此使用FileReader
		try {
			// 建立一个File
			f = new File(directory, filename);
			// 建立一个FileReader
			in = new FileReader(f);
			// 一次读取4K的characters
			char[] buffer = new char[4096];
			int len;
			// 清除文本区
			textarea.setText("");
			while ((len = in.read(buffer)) != -1) { // 读取一批char
				String s = new String(buffer, 0, len); // 转成字符串
				textarea.append(s); // 显示
			}
			// 改变主窗口的标题
			parent.setTitle(filename + " - JavaEditor");
			// 移入文本区开始的地方
			textarea.setCaretPosition(0);
		}
		// 如果有错误信息，显示
		catch (IOException e) {
			textarea.setText(e.getClass().getName() + ": " + e.getMessage());
			parent.setTitle("FileViewer: " + filename + ": I/O Exception");
		}
		// 最后总是关闭流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 对新的文本区加入监听
		textarea.getDocument().addDocumentListener(new DocumentChangeListener(parent.getTabbedPane()));
	}

	/**
	 * 获得文件扩展名.
	 * 
	 * @param fileName the file name
	 * 
	 * @return the file name ext
	 */
	public static String getFileNameExt(String fileName) {
		String fileExt = "";
		if (fileName.indexOf('.') > -1) {
			fileExt = fileName.substring(fileName.lastIndexOf('.'));
		}
		return fileExt;
	}

}
