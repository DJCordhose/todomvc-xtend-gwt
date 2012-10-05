package org.eclipse.xtend.gwt.todomvc.shared

import java.io.Serializable

class Todo implements Serializable {
	@Property String title
	@Property boolean done
}