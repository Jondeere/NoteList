package notelist.impl;

public class NoteItem {

	private boolean isDone;
	private String noteText;
	private String dateText;

	public NoteItem(boolean isDone, String noteText, String dateText) {
		this.isDone = isDone;
		this.noteText = noteText;
		this.dateText = dateText;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public String getNoteText() {
		return noteText;
	}

	public String getDateText() {
		return dateText;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(dateText);
		builder.append(": ");
		builder.append(noteText);
		return builder.toString();
	}

}
