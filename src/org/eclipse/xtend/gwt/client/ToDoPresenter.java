package org.eclipse.xtend.gwt.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.xtend.gwt.shared.Todo;
import org.eclipse.xtend.gwt.shared.TodoService;
import org.eclipse.xtend.gwt.shared.TodoServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;

/**
 * The presenter for the ToDo application. This class is responsible for the lifecycle of the
 * {@link Todo} instances.
 *
 * @author ceberhardt
 *
 */
public class ToDoPresenter {
	
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

	/**
	 * Handler for view events, defers to private presenter methods.
	 */
	private final ViewEventHandler viewHandler = new ViewEventHandler() {
		@Override
		public void addTask() {
			ToDoPresenter.this.addTask();
		}

		@Override
		public void clearCompletedTasks() {
			ToDoPresenter.this.clearCompletedTasks();
		}

		@Override
		public void markAllCompleted(boolean completed) {
			ToDoPresenter.this.markAllCompleted(completed);
		}

		@Override
		public void deleteTask(Todo toDoItem) {
			ToDoPresenter.this.deleteTask(toDoItem);
		}

		@Override
		public void updateTask(Todo todo) {
			ToDoPresenter.this.updateTask(todo);
		}
	};

	private List<Todo> todos = new ArrayList<Todo>();

	private final View view;

	public ToDoPresenter(View view) {
		this.view = view;
		view.addhandler(viewHandler);

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
	protected void deleteTask(Todo toDoItem) {
		todos.remove(toDoItem);
		updateTaskStatistics();
		saveState();
	}
	
	/**
	 * Invoked by a task when its state changes so that we can update the view statistics and persist.
	 */
	protected void updateTask(Todo toDoItem) {
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
	private void markAllCompleted(boolean completed) {
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
	private void addTask() {
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
	private void clearCompletedTasks() {
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
