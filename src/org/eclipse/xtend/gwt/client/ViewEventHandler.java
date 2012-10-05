package org.eclipse.xtend.gwt.client;

import org.eclipse.xtend.gwt.shared.Todo;

/**
 * The interface that handles interactions from the view.
 *
 */
public interface ViewEventHandler {
	/**
	 * Invoked when a user deletes a new task.
	 */
	void deleteTask(Todo todo);
	
	void updateTask(Todo todo);
	
	/**
	 * Invoked when a user adds a new task.
	 */
	void addTask();

	/**
	 * Invoked when a user wishes to clear completed tasks.
	 */
	void clearCompletedTasks();

	/**
	 * Sets the completed state of all tasks to the given state
	 */
	void markAllCompleted(boolean completed);
}