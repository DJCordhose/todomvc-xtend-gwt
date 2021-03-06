package org.eclipse.xtend.gwt.todomvc.server

import de.itemis.xtend.auto.gwt.GwtService
import java.util.List
import org.eclipse.xtend.gwt.todomvc.shared.Todo

import static com.google.appengine.api.memcache.MemcacheServiceFactory.*

@GwtService class TodoServiceImpl {

	override List<Todo> load(String name) {
		memcacheService.get(name) as List<Todo>
	}

	override void save(List<Todo> todos, String name) {
		memcacheService.put(name, todos)
	}

}
