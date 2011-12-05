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

	/** The parent. ������ */
	private MainWindow parent;

	/** The textarea. �ı��� */
	private JTextArea textarea;

	/** The directory. �ı��ļ��� */
	private String directory;

	/** The filename. �ı����� */
	private String filename;

	/**
	 * Instantiates a new file loader. ��ʼ��
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
	 * ��������һ���߳��������ı�
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if ((filename == null) || (filename.length() == 0))
			return;
		File f;
		FileReader in = null;

		// ��ȡչʾ���ݣ���Ϊ��ȡ���Ǵ��ı������ʹ��FileReader
		try {
			// ����һ��File
			f = new File(directory, filename);
			// ����һ��FileReader
			in = new FileReader(f);
			// һ�ζ�ȡ4K��characters
			char[] buffer = new char[4096];
			int len;
			// ����ı���
			textarea.setText("");
			while ((len = in.read(buffer)) != -1) { // ��ȡһ��char
				String s = new String(buffer, 0, len); // ת���ַ���
				textarea.append(s); // ��ʾ
			}
			// �ı������ڵı���
			parent.setTitle(filename + " - JavaEditor");
			// �����ı�����ʼ�ĵط�
			textarea.setCaretPosition(0);
		}
		// ����д�����Ϣ����ʾ
		catch (IOException e) {
			textarea.setText(e.getClass().getName() + ": " + e.getMessage());
			parent.setTitle("FileViewer: " + filename + ": I/O Exception");
		}
		// ������ǹر���
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ���µ��ı����������
		textarea.getDocument().addDocumentListener(new DocumentChangeListener(parent.getTabbedPane()));
	}

	/**
	 * ����ļ���չ��.
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
