package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.core.client.GWT
import com.google.gwt.storage.client.Storage
import com.google.gwt.user.client.Random
import com.google.gwt.user.client.ui.RootPanel
import java.util.List
import org.eclipse.xtend.gwt.todomvc.shared.Todo
import org.eclipse.xtend.gwt.todomvc.shared.TodoService
import org.eclipse.xtend.gwt.todomvc.shared.TodoServiceAsync

import static org.eclipse.xtend.gwt.AsyncCallbackExtensions.*

class ToDoPresenter implements EntryPoint {
	static val STORAGE_KEY = "TODO-USER"

	extension TodoServiceAsync service = GWT.create(TodoService)

	List<Todo> todos = newArrayList
	ToDoView view

	/**
	 * Gwt's main(String[])
	 */
	override onModuleLoad() {
		RootPanel.get.add(
			view = new ToDoView => [
				onAddTodo [
					// don't add a todo if todoText is empty
					if (view.todoText == null)
						return;
					todos += new Todo => [
						title = view.todoText
					]
					view.clearTodoText
					updateTodoStatistics
				]
				onClearCompletedTodos [
					todos = todos.filter[!done].toList
					updateTodoStatistics
				]
				onDeleteTodo [
					todos.remove(it)
					updateTodoStatistics
				]
				onMarkAllCompleted [ isCompleted |
					todos.forEach[done = isCompleted]
					updateTodoStatistics
				]
				onUpdateTodo [
					if (title.trim == "") {
						todos.remove(it)
					}
					updateTodoStatistics
				]
			]
		)

		// to initially display statistics without waiting for return of server call	
		view.setTodoStatistics(0, 0)
		service.load(currentName,
			onSuccess [
				todos = it
				if (todos == null) {
					todos = newArrayList
				}
				updateTodoStatistics
			])
	}

	/**
	 * Computes the todo statistics, updates the view and synchronizes 
	 * with the current state to the server
	 */
	private def updateTodoStatistics() {
		val totalTodos = todos.size
		var completeTodos = todos.filter[done].size
		view.setTodoStatistics(totalTodos, completeTodos)
		view.updateView(todos)
		todos.save(currentName, onSuccess [])
	}

	private def getCurrentName() {
		var currentName = "name" + Random.nextInt
		val Storage storage = Storage.getLocalStorageIfSupported
		if (storage != null) {
			val storedName = storage.getItem(STORAGE_KEY);
			if (storedName == null || storedName.equals("")) {
				storage.setItem(STORAGE_KEY, currentName)
			} else {
				currentName = storedName
			}
		}
		return currentName
	}

}
