package notelist.impl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class NoteItemList extends JList<NoteItem> {
	public NoteItemList(DefaultListModel<NoteItem> model) {
		setModel(model);
		setCellRenderer(new ListCheckBoxRenderer());

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = locationToIndex(e.getPoint());
					if (index != -1) {
						NoteItem item = (NoteItem) getModel().getElementAt(index);
						item.setDone(!item.isDone());
						repaint();
					}
				}
			}
		});

		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}