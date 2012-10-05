package org.eclipse.xtend.gwt.client;

import org.eclipse.xtend.gwt.shared.Todo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A view for the {@link ToDoPresenter}
 * 
 */
public class TaskComposite extends Composite {

	private static TaskCompositeUiBinder uiBinder = GWT
			.create(TaskCompositeUiBinder.class);

	interface TaskCompositeUiBinder extends UiBinder<Widget, TaskComposite> {
	}

	@UiField
	LIElement li;

	@UiField
	TextBox textBox;

	@UiField
	Label label;

	@UiField
	CheckBox checkBox;

	@UiField
	Button removeButton;

	private Todo todo;
	private ViewEventHandler viewHandler;

	public TaskComposite() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskComposite(Todo todo, ViewEventHandler viewHandler) {
		this();
		this.viewHandler = viewHandler;
		setTask(todo);
	}

	@UiHandler("label")
	public void editTask(DoubleClickEvent event) {
		editMode();
	}

	@UiHandler("textBox")
	public void editTaskComplete(BlurEvent event) {
		finishEditing();
	}

	@UiHandler("textBox")
	public void enter(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			finishEditing();
		} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			viewMode();
			resetTask();
		}
	}

	@UiHandler("checkBox")
	public void toggleDoneState(ClickEvent event) {
		viewHandler.updateTask(getTask());
	}

	@UiHandler("removeButton")
	public void removeTask(ClickEvent event) {
		viewHandler.deleteTask(todo);
	}

	public Todo getTask() {
		todo.setDone(checkBox.getValue());
		todo.setTitle(textBox.getText());
		return todo;
	}

	public void setTask(Todo task) {
		this.todo = task;
		textBox.setText(task.getTitle());
		label.setText(task.getTitle());
		checkBox.setValue(task.isDone());
		if (task.isDone()) {
			li.setAttribute("class", "completed");
		} else {
			li.removeAttribute("class");
		}
	}
	
	public void resetTask() {
		setTask(todo);
	}

	private void editMode() {
		li.setAttribute("class", "editing");
		textBox.setFocus(true);
	}

	private void viewMode() {
		li.removeAttribute("class");
	}

	private void finishEditing() {
		viewMode();
		viewHandler.updateTask(getTask());
	}

}