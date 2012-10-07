package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.event.dom.client.KeyCodes
import com.google.gwt.dom.client.Element
import com.google.gwt.user.client.DOM
import com.google.gwt.user.client.Event
import com.google.gwt.user.client.ui.Composite
import java.util.List
import org.eclipse.xtend.gwt.todomvc.shared.Todo
import org.eclipse.xtend.gwt.ui.WithUiBinding

@WithUiBinding
class ToDoView extends Composite implements View {
	
	ViewEventHandler viewHandler
	
	override addhandler(ViewEventHandler handler) {
		this.viewHandler = handler
		initWidget(uiBinder.createAndBindUi(this))
		
		mainSection.setId("main");
		clearCompleted.getElement().setId("clear-completed");
		taskText.getElement().setId("new-todo");
		todoStatsContainer.setId("footer");
		toggleAll.setId("toggle-all");
		
		taskText.addKeyUpHandler [
			if (nativeKeyCode == KeyCodes::KEY_ENTER)
				handler.addTask
		]

		clearCompleted.addClickHandler [
			handler.clearCompletedTasks
		]
		
		val com.google.gwt.user.client.Element clientToggleElement = toggleAll.cast
		DOM::sinkEvents(clientToggleElement, Event::ONCLICK)
		DOM::setEventListener(clientToggleElement) [
			handler.markAllCompleted(toggleAll.isChecked)
		] 
	}
	
	override clearTaskText() {
		taskText.text = ''
	}
	
	override getTaskText() {
		taskText.text
	}
	
	override updateView(List<Todo> list) {
		todoPanel.clear
		for (Todo todo : list) {
			val taskComposite = new TodoComposite(todo, viewHandler)
			todoPanel.add(taskComposite)
		}
	}
	
	override setTaskStatistics(int totalTasks, int completedTasks) {
		val remainingTasks = totalTasks - completedTasks

		hideElement(mainSection, totalTasks == 0)
		hideElement(todoStatsContainer, totalTasks == 0)
		hideElement(clearCompleted.element, completedTasks == 0)

		remainingTasksCount.innerText = remainingTasks.toString
		remainingTasksLabel.innerText = 
			if (remainingTasks > 1 || remainingTasks == 0) "items" else "item"
		clearTasksCount.innerHTML = completedTasks.toString

		toggleAll.checked = totalTasks == completedTasks
	}
	
	def private void hideElement(Element element, boolean hide) {
		if (hide) {
			element.setAttribute("style", "display:none;");
		} else {
			element.setAttribute("style", "display:block;");
		}
	}
	
	
}