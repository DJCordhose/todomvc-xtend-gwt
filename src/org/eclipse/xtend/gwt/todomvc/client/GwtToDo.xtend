package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.user.client.ui.RootPanel

class GwtToDo implements EntryPoint {
	override onModuleLoad() {
		RootPanel::get.add(
			new ToDoView => [
				new ToDoPresenter(it)
			]
		)
	}
}