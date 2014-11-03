package org.eclipse.xtend.gwt.todomvc.shared

import java.io.Serializable
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors class Todo implements Serializable {
	String title
	boolean done
}
