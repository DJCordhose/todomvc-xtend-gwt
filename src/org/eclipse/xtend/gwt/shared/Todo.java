package org.eclipse.xtend.gwt.shared;

import java.io.Serializable;

@SuppressWarnings("all")
public class Todo implements Serializable {

	private String title;
	private boolean done;

	public Todo() {
	}

	public Todo(String title) {
		this.title = title;
		this.done = false;
	}

	public boolean isDone() {
		return this.done;
	}

	public void setDone(final boolean done) {
		this.done = done;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title + "(" + (done ? "done" : "open") + ")";
	}
}
