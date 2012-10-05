package org.eclipse.xtend.gwt.todomvc.client;

import com.google.common.base.Objects;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import org.eclipse.xtend.gwt.todomvc.client.ToDoView.ViewEventHandler;
import org.eclipse.xtend.gwt.todomvc.shared.Todo;
import org.eclipse.xtend.gwt.ui.UiBuilder;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TodoComposite extends Composite {
  private HTMLPanel li;
  
  private TextBox textBox;
  
  private CheckBox checkBox;
  
  private Label label;
  
  private Todo todo;
  
  private ViewEventHandler viewHandler;
  
  public TodoComposite(final Todo todo, final ViewEventHandler viewHandler) {
    this.todo = todo;
    this.viewHandler = viewHandler;
    HTMLPanel _createWidget = this.createWidget();
    this.initWidget(_createWidget);
    this.updateView();
  }
  
  public HTMLPanel createWidget() {
    final Procedure1<HTMLPanel> _function = new Procedure1<HTMLPanel>() {
        public void apply(final HTMLPanel it) {
          final Procedure1<TextBox> _function = new Procedure1<TextBox>() {
              public void apply(final TextBox it) {
                it.setStyleName("edit");
                final Procedure1<BlurEvent> _function = new Procedure1<BlurEvent>() {
                    public void apply(final BlurEvent it) {
                      String _value = TodoComposite.this.textBox.getValue();
                      TodoComposite.this.todo.setTitle(_value);
                      TodoComposite.this.viewMode();
                      TodoComposite.this.viewHandler.updateTask(TodoComposite.this.todo);
                    }
                  };
                UiBuilder.onBlur(it, new BlurHandler() {
                    public void onBlur(BlurEvent event) {
                      _function.apply(event);
                    }
                });
                final Procedure1<KeyPressEvent> _function_1 = new Procedure1<KeyPressEvent>() {
                    public void apply(final KeyPressEvent it) {
                      NativeEvent _nativeEvent = it.getNativeEvent();
                      int _keyCode = _nativeEvent.getKeyCode();
                      final int _switchValue = _keyCode;
                      boolean _matched = false;
                      if (!_matched) {
                        if (Objects.equal(_switchValue,KeyCodes.KEY_ENTER)) {
                          _matched=true;
                          String _value = TodoComposite.this.textBox.getValue();
                          TodoComposite.this.todo.setTitle(_value);
                          TodoComposite.this.viewMode();
                          TodoComposite.this.viewHandler.updateTask(TodoComposite.this.todo);
                        }
                      }
                      if (!_matched) {
                        if (Objects.equal(_switchValue,KeyCodes.KEY_ESCAPE)) {
                          _matched=true;
                          TodoComposite.this.viewMode();
                          TodoComposite.this.viewHandler.updateTask(TodoComposite.this.todo);
                        }
                      }
                    }
                  };
                UiBuilder.onKeyPress(it, new KeyPressHandler() {
                    public void onKeyPress(KeyPressEvent event) {
                      _function_1.apply(event);
                    }
                });
              }
            };
          TextBox _textBox = UiBuilder.textBox(it, _function);
          TodoComposite.this.textBox = _textBox;
          final Procedure1<FlowPanel> _function_1 = new Procedure1<FlowPanel>() {
              public void apply(final FlowPanel it) {
                it.setStyleName("view");
                final Procedure1<CheckBox> _function = new Procedure1<CheckBox>() {
                    public void apply(final CheckBox it) {
                      it.setStyleName("toggle");
                      final Procedure1<ClickEvent> _function = new Procedure1<ClickEvent>() {
                          public void apply(final ClickEvent it) {
                            boolean _isDone = TodoComposite.this.todo.isDone();
                            boolean _not = (!_isDone);
                            TodoComposite.this.todo.setDone(_not);
                            TodoComposite.this.updateView();
                            TodoComposite.this.viewHandler.updateTask(TodoComposite.this.todo);
                          }
                        };
                      UiBuilder.onClick(it, new ClickHandler() {
                          public void onClick(ClickEvent event) {
                            _function.apply(event);
                          }
                      });
                    }
                  };
                CheckBox _checkBox = UiBuilder.checkBox(it, _function);
                TodoComposite.this.checkBox = _checkBox;
                final Procedure1<Label> _function_1 = new Procedure1<Label>() {
                    public void apply(final Label it) {
                      final Procedure1<DoubleClickEvent> _function = new Procedure1<DoubleClickEvent>() {
                          public void apply(final DoubleClickEvent it) {
                            TodoComposite.this.editMode();
                          }
                        };
                      UiBuilder.onDoubleClick(it, new DoubleClickHandler() {
                          public void onDoubleClick(DoubleClickEvent event) {
                            _function.apply(event);
                          }
                      });
                    }
                  };
                Label _label = UiBuilder.label(it, _function_1);
                TodoComposite.this.label = _label;
                final Procedure1<Button> _function_2 = new Procedure1<Button>() {
                    public void apply(final Button it) {
                      it.setStyleName("destroy");
                      final Procedure1<ClickEvent> _function = new Procedure1<ClickEvent>() {
                          public void apply(final ClickEvent it) {
                            TodoComposite.this.viewHandler.deleteTask(TodoComposite.this.todo);
                          }
                        };
                      UiBuilder.onClick(it, new ClickHandler() {
                          public void onClick(ClickEvent event) {
                            _function.apply(event);
                          }
                      });
                    }
                  };
                UiBuilder.button(it, _function_2);
              }
            };
          UiBuilder.flowPanel(it, _function_1);
        }
      };
    HTMLPanel _htmlPanel = UiBuilder.htmlPanel("li", _function);
    HTMLPanel _li = this.li = _htmlPanel;
    return _li;
  }
  
  public void viewMode() {
    this.updateView();
    Element _element = this.li.getElement();
    _element.removeAttribute("class");
  }
  
  public void editMode() {
    this.updateView();
    Element _element = this.li.getElement();
    _element.setAttribute("class", "editing");
    this.textBox.setFocus(true);
  }
  
  public void updateView() {
    String _title = this.todo.getTitle();
    this.textBox.setText(_title);
    String _title_1 = this.todo.getTitle();
    this.label.setText(_title_1);
    boolean _isDone = this.todo.isDone();
    this.checkBox.setValue(Boolean.valueOf(_isDone));
    boolean _isDone_1 = this.todo.isDone();
    if (_isDone_1) {
      Element _element = this.li.getElement();
      _element.setAttribute("class", "completed");
    } else {
      Element _element_1 = this.li.getElement();
      _element_1.removeAttribute("class");
    }
  }
}
