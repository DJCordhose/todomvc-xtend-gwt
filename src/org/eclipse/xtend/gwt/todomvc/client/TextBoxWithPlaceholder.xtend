package org.eclipse.xtend.gwt.todomvc.client

import com.google.gwt.user.client.ui.TextBox

class TextBoxWithPlaceholder extends TextBox {

	/**
	 * Sets the placeholder for this textbox
	 *
	 * @param value the placeholder value
	 */
	def void setPlaceholder(String value) {
		element.setAttribute("placeholder", value)
	}

	/**
	 * Gets the placeholder for this textbox
	 *
	 * @return the placeholder
	 */
	def String getPlaceholder() {
		return element.getAttribute("placeholder")
	}
}