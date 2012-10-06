package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.user.client.ui.RootPanel
import com.google.gwt.storage.client.Storage
import com.google.gwt.user.client.Random

class GwtToDo implements EntryPoint {
	
	static val STORAGE_KEY = "TODO-USER"
	
	override onModuleLoad() {
		RootPanel::get.add(
			new ToDoView => [
				new ToDoPresenter(it, getCurrentName())
			]
		)
	}
	
	def getCurrentName() {
		var currentName = "name" + Random::nextInt()
		val Storage storage = Storage::getLocalStorageIfSupported();
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