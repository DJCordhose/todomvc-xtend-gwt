package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.event.dom.client.KeyCodes
import com.google.gwt.user.client.ui.CheckBox
import com.google.gwt.user.client.ui.Composite
import com.google.gwt.user.client.ui.HTMLPanel
import com.google.gwt.user.client.ui.Label
import com.google.gwt.user.client.ui.TextBox
import org.eclipse.xtend.gwt.todomvc.shared.Todo

import static extension org.eclipse.xtend.gwt.ui.UiBuilder.*

class TodoComposite extends Composite {
	
	HTMLPanel li
	TextBox textBox 
	CheckBox checkBox
	Label label
	
	Todo todo
	ViewEventHandler viewHandler
	
	new(Todo todo, ViewEventHandler viewHandler) {
		this.todo = todo
		this.viewHandler = viewHandler
		initWidget(createWidget())
		updateView()
	}
	
	def createWidget() {
		li = 'li'.htmlPanel [
			textBox = textBox [
				styleName = 'edit'
				onBlur [
					todo.title = textBox.value
					viewMode
					viewHandler.updateTask(todo)
				]
				onKeyPress [
					switch nativeEvent.keyCode {
						case KeyCodes::KEY_ENTER : {
							todo.title = textBox.value
							viewMode
							viewHandler.updateTask(todo)
						}
						case KeyCodes::KEY_ESCAPE : {
							viewMode
							viewHandler.updateTask(todo)
						}
					}
				]
			]
			flowPanel [
				styleName = 'view'
				checkBox = checkBox [
					styleName = 'toggle'
					onClick [
						todo.done = !todo.done
						updateView
						viewHandler.updateTask(todo)
					]
				]
				label = label [
					onDoubleClick [
						editMode
					]
				]
				button [
					styleName = 'destroy'
					onClick [
						viewHandler.deleteTask(todo)
					]
				]
			]
		]
	}
	
	def viewMode() {
		updateView
		li.element.removeAttribute('class')
	}
	
	def editMode() {
		updateView
		li.element.setAttribute('class','editing')
		textBox.focus = true
	}
	
	def updateView() {
		textBox.text = todo.title
		label.text = todo.title
		checkBox.value = todo.done
		if (todo.done) {
			li.element.setAttribute("class", "completed")
		} else {
			li.element.removeAttribute("class")
		}
	}
	
}