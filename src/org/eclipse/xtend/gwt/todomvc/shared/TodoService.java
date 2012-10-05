package org.eclipse.xtend.gwt.todomvc.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("todoService")
public interface TodoService extends RemoteService {
	public List<Todo> load(String name);

	public void save(String name, List<Todo> todos);

}
