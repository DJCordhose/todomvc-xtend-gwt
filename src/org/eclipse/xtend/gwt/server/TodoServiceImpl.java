package org.eclipse.xtend.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.xtend.gwt.shared.Todo;
import org.eclipse.xtend.gwt.shared.TodoService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("all")
public class TodoServiceImpl extends RemoteServiceServlet implements
		TodoService {
	private static ConcurrentMap<String, List<Todo>> TODOS = new ConcurrentHashMap<String, List<Todo>>();

	@Override
	public List<Todo> load(String name) {
		TODOS.putIfAbsent(name, new ArrayList<Todo>());
		List<Todo> todos = TODOS.get(name);
		return todos;
	}

	@Override
	public void save(String name, List<Todo> todos) {
		TODOS.put(name, todos);
	}
}
