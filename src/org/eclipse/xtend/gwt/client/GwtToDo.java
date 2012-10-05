package org.eclipse.xtend.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point class
 */
public class GwtToDo implements EntryPoint {

	private final static String STORAGE_KEY = "TODO-USER";
	
	@Override
	public void onModuleLoad() {
		String currentName = getCurrentName();
		ToDoView toDoView = new ToDoView();
		new ToDoPresenter(toDoView, currentName);
		RootPanel.get().add(toDoView);
	}
	
	private String getCurrentName() {
		String currentName = "name" + Random.nextInt();
		Storage storage = Storage.getLocalStorageIfSupported();
		if (storage != null) {
			String storedName = storage.getItem(STORAGE_KEY);
			if (storedName == null || storedName.equals("")) {
				storage.setItem(STORAGE_KEY, currentName);
			} else {
				currentName = storedName;
			}
		}
		return currentName;
	}
}
