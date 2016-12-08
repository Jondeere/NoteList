package notelist.impl;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListCheckBoxRenderer extends JCheckBox implements ListCellRenderer<NoteItem> {

	@Override
	public Component getListCellRendererComponent(JList<? extends NoteItem> list, NoteItem value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setComponentOrientation(list.getComponentOrientation());
		setFont(list.getFont());
		setBackground(getBackgroudColot(list.getBackground(), isSelected));
		setForeground(list.getForeground());
		setSelected(value.isDone());
		setEnabled(list.isEnabled());
		setText(value.toString());

		return this;
	}

	private Color getBackgroudColot(Color defaultColor, boolean isSelected) {
		return isSelected ? Color.LIGHT_GRAY : defaultColor;
	}
}
