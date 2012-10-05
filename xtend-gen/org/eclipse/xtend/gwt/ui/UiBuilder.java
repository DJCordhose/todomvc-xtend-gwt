package org.eclipse.xtend.gwt.ui;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class UiBuilder {
  public static HandlerRegistration onBlur(final FocusWidget element, final BlurHandler handler) {
    HandlerRegistration _addBlurHandler = element.addBlurHandler(handler);
    return _addBlurHandler;
  }
  
  public static HandlerRegistration onKeyPress(final FocusWidget element, final KeyPressHandler handler) {
    HandlerRegistration _addKeyPressHandler = element.addKeyPressHandler(handler);
    return _addKeyPressHandler;
  }
  
  public static HandlerRegistration onClick(final FocusWidget element, final ClickHandler handler) {
    HandlerRegistration _addClickHandler = element.addClickHandler(handler);
    return _addClickHandler;
  }
  
  public static HandlerRegistration onDoubleClick(final Label element, final DoubleClickHandler handler) {
    HandlerRegistration _addDoubleClickHandler = element.addDoubleClickHandler(handler);
    return _addDoubleClickHandler;
  }
  
  public static CheckBox checkBox(final Panel parent, final Procedure1<? super CheckBox> initializer) {
    CheckBox _checkBox = new CheckBox();
    final CheckBox result = _checkBox;
    parent.add(result);
    initializer.apply(result);
    return result;
  }
  
  public static Button button(final Panel parent, final Procedure1<? super Button> initializer) {
    Button _button = new Button();
    final Button result = _button;
    parent.add(result);
    initializer.apply(result);
    return result;
  }
  
  public static Label label(final Panel parent, final Procedure1<? super Label> initializer) {
    Label _label = new Label();
    final Label result = _label;
    parent.add(result);
    initializer.apply(result);
    return result;
  }
  
  public static TextBox textBox(final Panel parent, final Procedure1<? super TextBox> initializer) {
    TextBox _textBox = new TextBox();
    final TextBox result = _textBox;
    parent.add(result);
    initializer.apply(result);
    return result;
  }
  
  public static FlowPanel flowPanel(final Panel parent, final Procedure1<? super FlowPanel> initializer) {
    FlowPanel _flowPanel = new FlowPanel();
    final FlowPanel result = _flowPanel;
    parent.add(result);
    initializer.apply(result);
    return result;
  }
  
  public static HTMLPanel htmlPanel(final String tagName, final Procedure1<? super HTMLPanel> initializer) {
    HTMLPanel _hTMLPanel = new HTMLPanel(tagName, "");
    final HTMLPanel result = _hTMLPanel;
    initializer.apply(result);
    return result;
  }
}
