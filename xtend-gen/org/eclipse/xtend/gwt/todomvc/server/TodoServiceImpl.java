package org.eclipse.xtend.gwt.todomvc.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.xtend.gwt.todomvc.shared.Todo;
import org.eclipse.xtend.gwt.todomvc.shared.TodoService;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class TodoServiceImpl extends RemoteServiceServlet implements TodoService {
  private final static ConcurrentHashMap<String,List<Todo>> TODOS = new Function0<ConcurrentHashMap<String,List<Todo>>>() {
    public ConcurrentHashMap<String,List<Todo>> apply() {
      ConcurrentHashMap<String,List<Todo>> _concurrentHashMap = new ConcurrentHashMap<String,List<Todo>>();
      return _concurrentHashMap;
    }
  }.apply();
  
  public List<Todo> load(final String name) {
    ArrayList<Todo> _newArrayList = CollectionLiterals.<Todo>newArrayList();
    TodoServiceImpl.TODOS.putIfAbsent(name, _newArrayList);
    final List<Todo> todos = TodoServiceImpl.TODOS.get(name);
    return todos;
  }
  
  public void save(final String name, final List<Todo> todos) {
    TodoServiceImpl.TODOS.put(name, todos);
  }
}
