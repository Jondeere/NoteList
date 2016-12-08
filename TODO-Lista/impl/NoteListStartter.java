package notelist.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NoteListStartter extends JPanel {

	public static void main(String s[]) {
		NoteListView view = new NoteListView("TODO-lista");
		new NoteListController(view);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(400, 300);
		view.setVisible(true);
	}
}
