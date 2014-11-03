GWT Xtend TodoMVC
=================

This implementation of the TodoMVC app uses GWT and Xtend. 
It has a backend running on the Google App Engine that is used to load and store your todos.

Try the app at http://1-dot-todomvc.appspot.com.

Why GWT?
--------

GWT allows you to use Java and all your tools and infrastructure for the client and for the server side. Using its widget 
library you can also abstract a bit from the DOM and browsers differences.

Why Xtend?
----------

For the client side you frequently have to specify callbacks. Those can be pretty verbose in Java. Xtend has a very
noise-less syntax for the same thing to specify. Additionally, a lot of boiler plate code can be saved by using
Xtends cool new 'Active Annotations'. 

Code Samples
------------

### List processing

    // filter out todos that are done
    todos = todos.filter[!done].toList

    // mark all todos as completed
    todos.forEach[done = true]

### UI Builder

      flowPanel [
		styleName = 'view'
		checkBox = checkBox [
			styleName = 'toggle'
			onClick [
				todo.done = !todo.done
				updateView
				updateTodo.apply(todo)
			]
		]
		label = label [
			onDoubleClick [
				editMode
			]
		]
		button [
			styleName = 'destroy'
			onClick [
				deleteTodo.apply(todo)
			]
		]
	]
