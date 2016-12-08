package notelist.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NoteListView extends JFrame {

	private JPanel mainPanel;
	private NoteItemList noteList;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;

	public NoteListView(String title) {
		super(title);
	}

	public void initialize(DefaultListModel<NoteItem> listModel) {
		mainPanel = new JPanel(new BorderLayout());
		noteList = new NoteItemList(listModel);
		JScrollPane pane = new JScrollPane(noteList);

		addButton = createButton("Lis‰‰");
		editButton = createButton("Muokkaa");
		removeButton = createButton("Poista");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(removeButton);

		mainPanel.add(pane, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
	}

	private JButton createButton(String text) {
		return new JButton(text);
	}

	public void addRemoveButtonActionListener(final ActionListener listener) {
		removeButton.addActionListener(listener);
	}

	public void addEditButtonActionListener(final ActionListener listener) {
		editButton.addActionListener(listener);
	}

	public void addAddButtonActionListener(final ActionListener listener) {
		addButton.addActionListener(listener);
	}

	public int getSelectedNoteIndex() {
		return noteList.getSelectedIndex();
	}

	public void setSelectedNoteIndex(int index) {
		noteList.setSelectedIndex(index);
	}

}
