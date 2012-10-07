package org.eclipse.xtend.gwt.todomvc.server

import com.google.appengine.api.memcache.MemcacheService
import com.google.appengine.api.memcache.MemcacheServiceFactory
import java.util.List
import org.eclipse.xtend.gwt.GwtService
import org.eclipse.xtend.gwt.todomvc.shared.Todo

@GwtService
class TodoServiceImpl {
	
	static val MemcacheService memcache = MemcacheServiceFactory::getMemcacheService()
	
	 override List<Todo> load(String name) {
		var todos = memcache.get(name)
		if (todos == null) {
			todos = newArrayList
		}
		return todos as List<Todo>
	}

	override void save(String name, List<Todo> todos) {
		memcache.put(name, todos)
	}
}