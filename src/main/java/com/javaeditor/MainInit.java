package com.javaeditor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class MainInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 主程序用一个线程启动
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// 设置外观
					LookAndFeel laf = new SubstanceBusinessBlackSteelLookAndFeel();
					try {
						UIManager.setLookAndFeel(laf);
					} catch (UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
					SubstanceLookAndFeel.setToUseConstantThemesOnDialogs(true);
					UIManager
							.put(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY,
									Boolean.TRUE);
					UIManager.put(SubstanceLookAndFeel.SHOW_EXTRA_WIDGETS,
							Boolean.TRUE);

					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					// 初始化主窗口
					MainWindow main = new MainWindow();

					// 添加关闭方法
					main.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

					// 设置初始大小
					main.setSize(800, 600);
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					Dimension frameSize = main.getSize();
					// 设置初始位置为屏幕中间
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}
					main.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					main.setVisible(true);

					// 设置初始时焦点移入编辑器中
					JTextArea edtor = (JTextArea) main.getEditorMap().get(
							main.getTabbedPane().getSelectedIndex());
					edtor.requestFocus();
				}
			});
		} catch (UnsupportedClassVersionError e) {
			// 版本异常提示
			String vers = System.getProperty("java.version");
			if (vers.compareTo("1.6.0") < 0) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error : 对不起！当前JVM是" + vers
								+ "。 JavaEditor必须运行在1.6.0或更高版本的JVM上", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}

	}
}
