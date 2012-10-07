package org.eclipse.xtend.gwt.todomvc.server

import com.google.appengine.api.memcache.MemcacheService
import com.google.appengine.api.memcache.MemcacheServiceFactory
import java.util.List
import org.eclipse.xtend.gwt.GwtService
import org.eclipse.xtend.gwt.todomvc.shared.Todo

@GwtService
class TodoServiceImpl {
	
	 override List<Todo> load(String name) {
		val MemcacheService memcache = MemcacheServiceFactory::getMemcacheService()
		var todos = memcache.get(name)
		if (todos == null) {
			todos = newArrayList
		}
		return todos as List<Todo>
	}

	override void save(String name, List<Todo> todos) {
		val MemcacheService memcache = MemcacheServiceFactory::getMemcacheService()
		memcache.put(name, todos)
	}
}