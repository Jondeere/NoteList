package notelist.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import notelist.NoteItemListener;

public class NoteListItemComponent {

	private JDialog frame;
	private JTextField noteTextField;
	private JFormattedTextField deadlineField;
	private SimpleDateFormat dateformatter;

	public NoteListItemComponent(Frame owner, String title, NoteItemListener listener) {
		dateformatter = new SimpleDateFormat("dd.MM.yyyy");
		dateformatter.setLenient(false);
		
		frame = new JDialog(owner, title, true);
		JPanel contentPanel = new JPanel(new GridLayout(2, 2));
		contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
		JLabel descriptionLabel = new JLabel("Selite:");
		noteTextField = new JTextField();
		noteTextField.setColumns(10);
		noteTextField.setHorizontalAlignment(JTextField.RIGHT);

		JLabel deadlineLabel = new JLabel("Deadline:");
		deadlineField = new JFormattedTextField(createDateMask());
		deadlineField.setColumns(10);
		deadlineField.setHorizontalAlignment(JTextField.RIGHT);

		contentPanel.add(descriptionLabel);
		contentPanel.add(noteTextField);
		contentPanel.add(deadlineLabel);
		contentPanel.add(deadlineField);

		JButton saveButton = new JButton("Tallenna");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateText = getDate();
				String description = getDescription();
				if (dateText != null && description != null){
					listener.noteItemCreated(dateText, description);
					closeDialog();
				}
			}
		});

		JButton cancelButton = new JButton("Peruuta");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(contentPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		frame.getContentPane().add(mainPanel);
		frame.pack();
	}
	
	public void setDeadLine(String deadlineText){
		deadlineField.setText(deadlineText);

	}
	
	public void setNoteText(String description){
		noteTextField.setText(description);
	}

	public void openView() {
		frame.setVisible(true);
	}

	private String getDate() {
		String value = (String) deadlineField.getText();
		if (value == null) {
			// empty date...
			deadlineField.setBackground(Color.YELLOW);
			return null;
		}

		try {
			dateformatter.parse(value);
			deadlineField.setBackground(Color.WHITE);
			return value;
		} catch (ParseException e1) {
			// invalid date...
			deadlineField.setBackground(Color.YELLOW);
			return null;
		}
	}

	private String getDescription() {
		String desc = noteTextField.getText();
		if (desc.isEmpty()) {
			noteTextField.setBackground(Color.YELLOW);
			return null;
		}
		
		noteTextField.setBackground(Color.WHITE);
		return desc;
	}

	private MaskFormatter createDateMask() {
		try {
			MaskFormatter dateMask = new MaskFormatter("##.##.####");
			dateMask.setPlaceholderCharacter('#');
			dateMask.setValidCharacters("0123456789");
			return dateMask;
		} catch (Exception e) {
			throw new RuntimeException("Invalid mask format.", e);
		}
	}

	private void closeDialog() {
		frame.setVisible(false);
		frame.dispose();
	}
}
