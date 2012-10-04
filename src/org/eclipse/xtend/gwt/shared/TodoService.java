package org.eclipse.xtend.gwt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

import org.eclipse.xtend.gwt.shared.Todo;

@RemoteServiceRelativePath("todoService")
public interface TodoService extends RemoteService {
	public List<Todo> load(String name);

	public void save(String name, List<Todo> todos);

}
