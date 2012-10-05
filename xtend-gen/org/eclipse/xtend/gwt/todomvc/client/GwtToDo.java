package org.eclipse.xtend.gwt.todomvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import org.eclipse.xtend.gwt.todomvc.client.ToDoPresenter;
import org.eclipse.xtend.gwt.todomvc.client.ToDoView;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class GwtToDo implements EntryPoint {
  public void onModuleLoad() {
    RootPanel _get = RootPanel.get();
    ToDoView _toDoView = new ToDoView();
    final Procedure1<ToDoView> _function = new Procedure1<ToDoView>() {
        public void apply(final ToDoView it) {
          new ToDoPresenter(it);
        }
      };
    ToDoView _doubleArrow = ObjectExtensions.<ToDoView>operator_doubleArrow(_toDoView, _function);
    _get.add(_doubleArrow);
  }
}
