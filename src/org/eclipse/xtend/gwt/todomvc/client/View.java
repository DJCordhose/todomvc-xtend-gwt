package org.eclipse.xtend.gwt.todomvc.client;

import java.util.List;

import org.eclipse.xtend.gwt.todomvc.shared.Todo;

/**
 * The interface that a view for this presenter must implement.
 */
public interface View {

	/**
	 * Gets the text that the user has input for the creation of new tasks.
	 */
	String getTaskText();

	/**
	 * Clears the user input field where new tasks are added.
	 */
	void clearTaskText();

	/**
	 * Sets the current task statistics.
	 */
	void setTaskStatistics(int totalTasks, int completedTasks);

	/**
	 * Adds the handler to the events raised by the view.
	 */
	void addhandler(ViewEventHandler handler);

	void updateView(List<Todo> list);
}