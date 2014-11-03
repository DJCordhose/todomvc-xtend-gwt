package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.dom.client.Element
import com.google.gwt.event.dom.client.KeyCodes
import com.google.gwt.user.client.DOM
import com.google.gwt.user.client.Event
import com.google.gwt.user.client.ui.Composite
import java.util.List
import org.eclipse.xtend.gwt.todomvc.shared.Todo
import org.eclipse.xtend.gwt.ui.WithUiBinding

@WithUiBinding class ToDoView extends Composite {

	new() {
		initWidget(UI_BINDER.createAndBindUi(this))

		// set stuff which can't be set in XML
		mainSection.id = 'main'
		clearCompleted.element.id = 'clear-completed'
		todoText.element.id = 'new-todo'
		todoText.element.setAttribute("placeholder", "What needs to be done?")
		todoStatsContainer.id = 'footer'
		toggleAll.id = 'toggle-all'
	}

	(Todo)=>void updateTodo

	def onUpdateTodo((Todo)=>void updateTodo) {
		this.updateTodo = updateTodo
	}

	(Todo)=>void deleteTodo

	def onDeleteTodo((Todo)=>void deleteTodo) {
		this.deleteTodo = deleteTodo
	}

	def onAddTodo((Void)=>void callback) {
		todoText.addKeyUpHandler [
			if (nativeKeyCode == KeyCodes::KEY_ENTER)
				callback.apply(null)
		]
	}

	def onClearCompletedTodos((Void)=>void callback) {
		clearCompleted.addClickHandler [
			callback.apply(null)
		]
	}

	def onMarkAllCompleted((Boolean)=>void handler) {
		val Element clientToggleElement = toggleAll.cast
		DOM.sinkEvents(clientToggleElement, Event.ONCLICK)
		DOM.setEventListener(clientToggleElement) [
			handler.apply(toggleAll.isChecked)
		]
	}

	def clearTodoText() {
		todoText.text = ''
	}

	def getTodoText() {
		val result = todoText.text?.trim
		if (result.nullOrEmpty)
			return null
		return result
	}

	def updateView(List<Todo> list) {
		todoPanel.clear
		for (todo : list) {
			val todoComposite = new TodoComposite(todo, updateTodo, deleteTodo)
			todoPanel.add(todoComposite)
		}
	}

	def setTodoStatistics(int totalTodos, int completedTodos) {
		val remainingTodos = totalTodos - completedTodos

		hideElement(mainSection, totalTodos == 0)
		hideElement(todoStatsContainer, totalTodos == 0)
		hideElement(clearCompleted.element, completedTodos == 0)

		remainingTodosCount.innerText = remainingTodos.toString
		remainingTodosLabel.innerText = if(remainingTodos > 1 || remainingTodos == 0) "items" else "item"
		clearTodosCount.innerHTML = completedTodos.toString

		toggleAll.checked = totalTodos == completedTodos
	}

	def private void hideElement(Element element, boolean hide) {
		if (hide) {
			element.setAttribute("style", "display:none;");
		} else {
			element.setAttribute("style", "display:block;");
		}
	}

}
