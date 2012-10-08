package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.core.client.GWT
import com.google.gwt.user.client.ui.RootPanel
import java.util.List
import org.eclipse.xtend.gwt.todomvc.shared.Todo
import org.eclipse.xtend.gwt.todomvc.shared.TodoService
import org.eclipse.xtend.gwt.todomvc.shared.TodoServiceAsync

import static org.eclipse.xtend.gwt.AsyncCallbackExtensions.*

class GwtToDo implements EntryPoint {
	
	extension TodoServiceAsync service = GWT::create(typeof(TodoService))
	
	List<Todo> todos = newArrayList
	ToDoView view
	
	/**
	 * Gwt's main(String[])
	 */
	override onModuleLoad() {
		service.load(onSuccess [
			todos = it
			if (todos == null) {
				todos = newArrayList
			}

			RootPanel::get.add(
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
					onMarkAllCompleted [ completed |
						todos.forEach[done = completed]
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
			updateTodoStatistics
		])
	}
	
	/**
	 * Computes the todo statistics, updates the view and synchronizes 
	 * with the current state to the server
	 */
	def private updateTodoStatistics() {
		val totalTodos = todos.size
		var completeTodos = todos.filter[done].size
		view.setTodoStatistics(totalTodos, completeTodos)
		todos.save(onSuccess [
			view.updateView(todos)
		])
	}

}