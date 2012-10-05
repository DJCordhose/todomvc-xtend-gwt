package org.eclipse.xtend.gwt.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.xtend.gwt.client.ToDoView.ViewEventHandler;
import org.eclipse.xtend.gwt.shared.Todo;
import org.eclipse.xtend.gwt.shared.TodoService;
import org.eclipse.xtend.gwt.shared.TodoServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The presenter for the ToDo application. This class is responsible for the lifecycle of the
 * {@link Todo} instances.
 *
 * @author ceberhardt
 *
 */
public class ToDoPresenter implements ViewEventHandler {
	
	private TodoServiceAsync service = GWT.create(TodoService.class);

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
	
	private List<Todo> todos = new ArrayList<Todo>();

	private final View view;

	public ToDoPresenter(View view) {
		this.view = view;
		view.addhandler(this);

		loadState();
	}

	/**
	 * Computes the tasks statistics and updates the view.
	 */
	private void updateTaskStatistics() {
		int totalTasks = todos.size();

		int completeTask = 0;
		for (Todo task : todos) {
			if (task.isDone()) {
				completeTask++;
			}
		}

		view.setTaskStatistics(totalTasks, completeTask);
	}

	/**
	 * Deletes the given task and updates statistics.
	 */
	public void deleteTask(Todo toDoItem) {
		todos.remove(toDoItem);
		updateTaskStatistics();
		saveState();
	}
	
	/**
	 * Invoked by a task when its state changes so that we can update the view statistics and persist.
	 */
	public void updateTask(Todo toDoItem) {
		// if the item has become empty, remove it
		if (toDoItem.getTitle().trim().equals("")) {
			todos.remove(toDoItem);
		}

		updateTaskStatistics();
		saveState();
	}

	/**
	 * Sets the completed state of all tasks
	 */
	public void markAllCompleted(boolean completed) {
		// update the completed state of each item
		for (Todo task : todos) {
			task.setDone(completed);
		}
		updateTaskStatistics();
		saveState();
	}

	/**
	 * Adds a new task based on the user input field
	 */
	public void addTask() {
		String taskTitle = view.getTaskText().trim();

		// if white-space only, do not add a todo
		if (taskTitle.equals(""))
			return;

		Todo toDoItem = new Todo(taskTitle);
		view.clearTaskText();
		todos.add(toDoItem);
		updateTaskStatistics();
		saveState();
	}

	/**
	 * Clears completed tasks and updates the view.
	 */
	public void clearCompletedTasks() {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo item = iterator.next();
			if (item.isDone()) {
				iterator.remove();
			}
		}
		updateTaskStatistics();
		saveState();
	}

	/**
	 * Saves the current to-do items to local storage
	 */
	private void saveState() {
		String name = getCurrentName();
		service.save(name, new ArrayList<Todo>(todos), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				updateView(todos);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Saving todos failed", caught);
			}
		});
	}

	private void loadState() {
		String name = getCurrentName();
		service.load(name, new AsyncCallback<List<Todo>>() {
			
			@Override
			public void onSuccess(List<Todo> result) {
				todos = result;
				updateTaskStatistics();
				updateView(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Loading todos failed", caught);
			}
		});
	}

	// TODO
	private String getCurrentName() {
		return "dummy";
	}
	

	private void updateView(List<Todo> list) {
		view.updateView(list);
	}

}
