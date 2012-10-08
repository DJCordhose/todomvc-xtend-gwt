package org.eclipse.xtend.gwt.todomvc.server

import com.google.common.collect.Lists
import java.util.List
import org.eclipse.xtend.gwt.GwtService
import org.eclipse.xtend.gwt.todomvc.shared.Todo

import static com.google.appengine.api.memcache.MemcacheServiceFactory.*

@GwtService
class TodoServiceImpl {
	
	 override List<Todo> load() {
		var todos = memcacheService.get(threadLocalRequest.session.id)
		if (todos == null) {
			todos = Lists::newArrayList
		}
		return todos as List<Todo>
	}

	override void save(List<Todo> todos) {
		memcacheService.put(threadLocalRequest.session.id, todos)
	}
}