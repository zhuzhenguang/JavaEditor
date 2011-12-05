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
			// ��������һ���߳�����
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// �������
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

					// ��ʼ��������
					MainWindow main = new MainWindow();

					// ��ӹرշ���
					main.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

					// ���ó�ʼ��С
					main.setSize(800, 600);
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					Dimension frameSize = main.getSize();
					// ���ó�ʼλ��Ϊ��Ļ�м�
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}
					main.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					main.setVisible(true);

					// ���ó�ʼʱ��������༭����
					JTextArea edtor = (JTextArea) main.getEditorMap().get(
							main.getTabbedPane().getSelectedIndex());
					edtor.requestFocus();
				}
			});
		} catch (UnsupportedClassVersionError e) {
			// �汾�쳣��ʾ
			String vers = System.getProperty("java.version");
			if (vers.compareTo("1.6.0") < 0) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error : �Բ��𣡵�ǰJVM��" + vers
								+ "�� JavaEditor����������1.6.0����߰汾��JVM��", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}

	}
}
