package org.eclipse.xtend.gwt.todomvc.server

import java.util.List
import java.util.concurrent.ConcurrentHashMap
import org.eclipse.xtend.gwt.GwtService
import org.eclipse.xtend.gwt.todomvc.shared.Todo

@GwtService
class TodoServiceImpl {
	
	// TODO: Either migrate to memcache (supereasy)
	// https://developers.google.com/appengine/docs/java/memcache/overview
	// or
	// datastore
	static val TODOS = new ConcurrentHashMap<String, List<Todo>>()

	override List<Todo> load(String name) {
		TODOS.putIfAbsent(name, newArrayList)
		val todos = TODOS.get(name)
		return todos
	}

	override void save(String name, List<Todo> todos) {
		TODOS.put(name, todos)
	}
}