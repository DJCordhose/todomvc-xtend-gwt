package org.eclipse.xtend.gwt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

import org.eclipse.xtend.gwt.shared.Todo;

public interface TodoServiceAsync {

	void load(String name, AsyncCallback<List<Todo>> callback);

	void save(String name, List<Todo> todos, AsyncCallback<Void> callback);
}
