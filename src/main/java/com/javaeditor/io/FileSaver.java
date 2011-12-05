package com.javaeditor.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;

/**
 * The Class FileSaver.
 */
public class FileSaver extends Thread {

	/** The doc. */
	Document doc;

	/** The f. */
	File f;

	/** The parent. */
	JFrame parent;

	/**
	 * Instantiates a new file saver.
	 * 
	 * @param f the f
	 * @param doc the doc
	 * @param parent the parent
	 */
	public FileSaver(File f, Document doc, JFrame parent) {
		setPriority(4);
		this.f = f;
		this.doc = doc;
		this.parent = parent;
	}

	/**
	 * 单独的线程执行保存
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			// start writing
			Writer out = new FileWriter(f);
			Segment text = new Segment();
			text.setPartialReturn(true);
			int charsLeft = doc.getLength();
			int offset = 0;
			while (charsLeft > 0) {
				doc.getText(offset, Math.min(4096, charsLeft), text);
				out.write(text.array, text.offset, text.count);
				charsLeft -= text.count;
				offset += text.count;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			final String msg = e.getMessage();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JOptionPane.showMessageDialog(parent, "Could not save file: " + msg, "Error saving file", JOptionPane.ERROR_MESSAGE);
				}
			});
		} catch (BadLocationException e) {
			System.err.println(e.getMessage());
		}
	}
}
