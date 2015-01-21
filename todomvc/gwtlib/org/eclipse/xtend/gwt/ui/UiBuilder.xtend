package org.eclipse.xtend.gwt.ui

import com.google.gwt.event.dom.client.BlurHandler
import com.google.gwt.user.client.ui.Button
import com.google.gwt.user.client.ui.CheckBox
import com.google.gwt.user.client.ui.FlowPanel
import com.google.gwt.user.client.ui.FocusWidget
import com.google.gwt.user.client.ui.HTMLPanel
import com.google.gwt.user.client.ui.Label
import com.google.gwt.user.client.ui.Panel
import com.google.gwt.user.client.ui.TextBox
import com.google.gwt.event.dom.client.KeyPressHandler
import com.google.gwt.event.dom.client.ClickHandler
import com.google.gwt.event.dom.client.DoubleClickHandler
import com.google.gwt.user.client.ui.Widget
import com.google.gwt.user.client.ui.HasWidgets

class UiBuilder {

	def static <T extends Widget> T add(HasWidgets parent, T widget, (T)=>void init) {
		parent.add(widget)
		init.apply(widget) 
		return widget
	}
	
	def static checkBox(Panel parent, (CheckBox)=>void initializer) {
		val result = new CheckBox
		parent.add(result)
		initializer.apply(result)
		return result
	}
	
	def static button(Panel parent, (Button)=>void initializer) {
		val result = new Button
		parent.add(result)
		initializer.apply(result)
		return result
	}
	
	def static label(Panel parent, (Label)=>void initializer) {
		val result = new Label
		parent.add(result)
		initializer.apply(result)
		return result
	}
	
	def static textBox(Panel parent, (TextBox)=>void initializer) {
		val result = new TextBox
		parent.add(result)
		initializer.apply(result)
		return result
	}
	
	def static flowPanel(Panel parent, (FlowPanel)=>void initializer) {
		val result = new FlowPanel
		parent.add(result)
		initializer.apply(result)
		return result
	}
	
	def static htmlPanel(String tagName, (HTMLPanel)=>void initializer) {
		val result = new HTMLPanel(tagName,"")
		initializer.apply(result)
		return result
	}
}