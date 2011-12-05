package com.javaeditor.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.text.DefaultEditorKit.CopyAction;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.text.DefaultEditorKit.PasteAction;

import com.javaeditor.MainWindow;
import com.javaeditor.operation.edit.RedoAction;
import com.javaeditor.operation.edit.UndoAction;
import com.javaeditor.operation.file.NewAction;
import com.javaeditor.operation.file.OpenAction;
import com.javaeditor.operation.file.SaveAction;

/**
 * The Class CommonUtil.
 */
public class CommonUtil {

	/** The resources. 绑定资源 */
	private static ResourceBundle resources;

	/** The Constant toolKeys. 资源中的key */
	private final static String[] toolKeys = { "newImage", "openImage", "saveImage", "copyImage", "pasteImage", "cutImage", "redoImage", "undoImage" };

	/**
	 * Gets the toolkeys.
	 *
	 * @return the toolkeys
	 */
	public static String[] getToolkeys() {
		return toolKeys;
	}

	/**
	 * 静态初始化资源文件
	 */
	static {
		try {
			resources = ResourceBundle.getBundle("JavaEditor", Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.err.println("JavaEditor.properties not found");
			System.exit(1);
		}
	}

	/**
	 * Gets the resource string.获得一个资源名
	 *
	 * @param nm the nm
	 *
	 * @return the resource string
	 */
	public static String getResourceString(String nm) {
		String str;
		try {
			str = resources.getString(nm);
		} catch (MissingResourceException mre) {
			str = null;
		}
		return str;
	}

	/**
	 * Gets the resource.将资源路径转成url
	 *
	 * @param nm the nm
	 *
	 * @return the resource
	 */
	public static URL getResource(String nm) {
		final ClassLoader cl = Thread.currentThread().getContextClassLoader();
		return cl.getResource(getResourceString(nm));
	}

	/**
	 * Removes the flag.为修改文本加入"*"
	 *
	 * @param title the title
	 *
	 * @return the string
	 */
	public static String removeFlag(String title) {
		if (title.endsWith("*")) {
			return title.substring(0, title.length() - 1);
		} else {
			return title;
		}
	}

	/**
	 * Gets the all action.所有的action
	 *
	 * @param parent the parent
	 *
	 * @return the all action
	 */
	public static Map<String, Action> getAllAction(MainWindow parent) {
		Map<String, Action> actions = new HashMap<String, Action>();
		actions.put(toolKeys[0], new NewAction(parent));
		actions.put(toolKeys[1], new OpenAction(parent));
		actions.put(toolKeys[2], new SaveAction(parent));
		actions.put(toolKeys[3], new CopyAction());
		actions.put(toolKeys[4], new PasteAction());
		actions.put(toolKeys[5], new CutAction());
		actions.put(toolKeys[6], new RedoAction());
		actions.put(toolKeys[7], new UndoAction());

		return actions;
	}
}
