package org.eclipse.xtend.gwt.todomvc.client

import java.util.ArrayList
import java.util.Iterator
import java.util.List

import org.eclipse.xtend.gwt.todomvc.client.ViewEventHandler;
import org.eclipse.xtend.gwt.todomvc.shared.Todo
import org.eclipse.xtend.gwt.todomvc.shared.TodoService
import org.eclipse.xtend.gwt.todomvc.shared.TodoServiceAsync

import com.google.gwt.core.client.GWT

import static org.eclipse.xtend.gwt.AsyncCallbackExtensions.*

/**
 * The presenter for the ToDo application. This class is responsible for the lifecycle of the
 * {@link Todo} instances.
 *
 * @author ceberhardt
 *
 */
class ToDoPresenter implements ViewEventHandler {
	
	TodoServiceAsync service = GWT::create(typeof(TodoService))

	List<Todo> todos = new ArrayList<Todo>()

	View view

	String currentName

	new(View view, String currentName) {
		this.view = view
		this.currentName = currentName
		view.addhandler(this)
		loadState()
	}

	/**
	 * Computes the tasks statistics and updates the view.
	 */
	def updateTaskStatistics() {
		val totalTasks = todos.size()

		var completeTask = 0
		for (Todo task : todos) {
			if (task.isDone()) {
				// FIXME ++ and += do not work here, why?
//				completeTask += 1
				completeTask = completeTask + 1
			}
		}

		view.setTaskStatistics(totalTasks, completeTask)
	}

	/**
	 * Deletes the given task and updates statistics.
	 */
	override deleteTask(Todo toDoItem) {
		todos.remove(toDoItem);
		updateTaskStatistics();
		saveState();
	}
	
	/**
	 * Invoked by a task when its state changes so that we can update the view statistics and persist.
	 */
	override updateTask(Todo toDoItem) {
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
	override markAllCompleted(boolean completed) {
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
	override addTask() {
		val taskTitle = view.getTaskText().trim();

		// if white-space only, do not add a todo
		if (taskTitle.equals(""))
			return;

		val toDoItem = new Todo();
		toDoItem.setTitle(taskTitle);
		view.clearTaskText();
		todos.add(toDoItem);
		updateTaskStatistics();
		saveState();
	}

	/**
	 * Clears completed tasks and updates the view.
	 */
	 override clearCompletedTasks() {
		val Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			val Todo item = iterator.next();
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
	def private void saveState() {
		val name = getCurrentName()
		service.save(name, new ArrayList<Todo>(todos), onSuccess [
				updateView(todos);
		])
	}

	def private void loadState() {
		val String name = getCurrentName();
		service.load(name, onSuccess [result|
				todos = result;
				updateTaskStatistics();
				updateView(result);
		])
	}

	def private String getCurrentName() {
		return currentName;
	}

	def private void updateView(List<Todo> list) {
		view.updateView(list);
	}

}
