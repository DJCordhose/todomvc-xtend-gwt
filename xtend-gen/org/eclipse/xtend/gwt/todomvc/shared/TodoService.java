package org.eclipse.xtend.gwt.todomvc.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;
import org.eclipse.xtend.gwt.todomvc.shared.Todo;

@RemoteServiceRelativePath("todoService")
public interface TodoService extends RemoteService {
  public List<Todo> load(final String name);
  
  public void save(final String name, final List<Todo> todos);
}
