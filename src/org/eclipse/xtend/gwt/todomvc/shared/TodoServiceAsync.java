package org.eclipse.xtend.gwt.todomvc.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TodoServiceAsync {

	void load(String name, AsyncCallback<List<Todo>> callback);

	void save(String name, List<Todo> todos, AsyncCallback<Void> callback);
}
