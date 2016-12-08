package notelist.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import notelist.NoteItemListener;

public class NoteListController {

	private DefaultListModel<NoteItem> listModel;
	private NoteListView view;

	public NoteListController(NoteListView view) {
		this.view = view;

		listModel = new DefaultListModel<NoteItem>();
		this.view.initialize(listModel);
		addButtonsActionListerners();
	}

	private void addButtonsActionListerners() {
		view.addRemoveButtonActionListener(createRemoveActionListener());
		view.addEditButtonActionListener(createEditActionListener());
		view.addAddButtonActionListener(createAddActionListener());
	}

	private ActionListener createRemoveActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listModel.getSize() > 0 && view.getSelectedNoteIndex() >= 0)
					listModel.removeElementAt(view.getSelectedNoteIndex());

			}
		};
	}

	private ActionListener createEditActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = view.getSelectedNoteIndex();
				if (selected >= 0) {
					NoteItem item = listModel.get(selected);
					NoteItemListener listener = new NoteItemListener() {
						@Override
						public void noteItemCreated(String dateText, String noteText) {
							listModel.removeElementAt(selected);
							listModel.add(selected, new NoteItem(false, noteText, dateText));
							view.setSelectedNoteIndex(selected);
						}
					};
					NoteListItemComponent editComponent = new NoteListItemComponent(view, "Muokkaa teht‰v‰‰", listener);
					editComponent.setDeadLine(item.getDateText());
					editComponent.setNoteText(item.getNoteText());
					editComponent.openView();
				}
			}
		};
	}

	private ActionListener createAddActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NoteItemListener listener = new NoteItemListener() {
					@Override
					public void noteItemCreated(String dateText, String noteText) {
						listModel.addElement(new NoteItem(false, noteText, dateText));

					}
				};

				NoteListItemComponent addComponent = new NoteListItemComponent(view, "Lis‰‰ teht‰v‰", listener);
				addComponent.openView();
			}
		};
	}

}
