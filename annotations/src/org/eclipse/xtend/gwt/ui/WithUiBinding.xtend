package org.eclipse.xtend.gwt.ui

import com.google.gwt.uibinder.client.UiBinder
import com.google.gwt.uibinder.client.UiField
import com.google.gwt.user.client.ui.Widget
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.PrintStream
import java.lang.annotation.ElementType
import java.lang.annotation.Target
import java.util.List
import java.util.Map
import javax.xml.parsers.DocumentBuilderFactory
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.Visibility
import org.w3c.dom.Document
import org.w3c.dom.Node

@Target(ElementType.TYPE)
@Active(WithUiBindingProcessor)
annotation WithUiBinding {
}

class WithUiBindingProcessor extends AbstractClassProcessor {

	private static final String SRC = "/Users/kosyakov/Documents/workspaces/vaadin/todomvc-xtend-gwt/todomvc/src/"

	override doRegisterGlobals(ClassDeclaration it, extension RegisterGlobalsContext context) {
		registerInterface(uiBinderInterface(it))
	}

	override doTransform(MutableClassDeclaration it, extension TransformationContext context) {
		val uiBinderInterfaceType = findInterface(uiBinderInterface)
		uiBinderInterfaceType.extendedInterfaces = uiBinderInterfaceType.extendedInterfaces +
			#[typeof(UiBinder).newTypeReference(typeof(Widget).newTypeReference, it.newTypeReference)]

		addField('UI_BINDER',
			[
				static = true
				final = true
				type = uiBinderInterfaceType.newTypeReference
				initializer = [
					'''com.google.gwt.core.client.GWT.create(«uiBinderInterfaceType.simpleName».class)'''
				]
			])

		val in = new FileInputStream(uiXml)
		val dom = try {
			DocumentBuilderFactory.newInstance.newDocumentBuilder.parse(in)
		} catch (Exception io) {
			addError('''Error loading file '«uiXml»' : [«io.message»]''')
			val byteArrayOutputStream = new ByteArrayOutputStream
			io.printStackTrace(new PrintStream(byteArrayOutputStream))
			addError(byteArrayOutputStream.toString)
			null as Document
		}
		val fields = dom.getFields
		for (entry : fields.entrySet) {
			addField(entry.key,
				[
					type = findTypeGlobally(entry.value).newTypeReference
					visibility = Visibility::PROTECTED
					addAnnotation(typeof(UiField).newTypeReference.type)
				])
		}
	}

	def uiBinderInterface(ClassDeclaration it) {
		qualifiedName + '.' + simpleName + 'UiBinder'
	}

	def uiXml(MutableClassDeclaration it) {
		SRC + qualifiedName.substring(0, qualifiedName.length - simpleName.length).replaceAll('\\.', '/') + simpleName +
			".ui.xml"
	}

	def static Map<String, String> getFields(Document doc) {
		val Map<String, String> result = newHashMap
		val imports = getImports(doc)
		val nodes = allNodes(doc)
		for (n : nodes) {
			val name = n.attributes?.getNamedItem("ui:field")
			if (name != null) {
				result.put(name.nodeValue, typeName(n, imports))
			}
		}
		return result
	}

	def static List<Node> allNodes(Document doc) {
		val result = <Node>newArrayList()
		addNodes(doc, result)
		return result
	}

	def private static void addNodes(Node node, List<Node> collected) {
		collected += node
		var i = 0
		var max = node.childNodes.length
		while (i < max) {
			addNodes(node.childNodes.item(i), collected)
			i = i + 1
		}
	}

	def static getImports(Document doc) {
		val Map<String, String> result = newHashMap
		val attrs = doc.documentElement.attributes
		var i = 0
		while (i < attrs.length) {
			val att = attrs.item(i)
			if (att.nodeName.startsWith('xmlns:') && att.nodeValue.startsWith('urn:import:')) {
				val prefix = att.nodeName.substring(6)
				val packName = att.nodeValue.substring(11)
				result.put(prefix, packName)
			}
			i = i + 1
		}
		return result
	}

	def static String typeName(Node node, Map<String, String> imports) {
		val fullName = node.nodeName
		val idx = fullName.indexOf(':')
		val prefix = if(idx != -1) fullName.substring(0, idx) else null
		val name = if(idx != -1) fullName.substring(idx + 1) else fullName

		switch name {
			case 'input': 'com.google.gwt.dom.client.InputElement'
			case 'span': 'com.google.gwt.dom.client.SpanElement'
			case prefix != null: imports.get(prefix) + "." + name
			default: 'com.google.gwt.dom.client.Element'
		}
	}

}
