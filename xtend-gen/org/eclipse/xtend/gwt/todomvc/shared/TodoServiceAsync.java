package org.eclipse.xtend.gwt.todomvc.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.eclipse.xtend.gwt.todomvc.shared.Todo;

public interface TodoServiceAsync {
  public void load(final String name, final AsyncCallback<List<Todo>> result);
  
  public void save(final String name, final List<Todo> todos, final AsyncCallback<Void> result);
}
