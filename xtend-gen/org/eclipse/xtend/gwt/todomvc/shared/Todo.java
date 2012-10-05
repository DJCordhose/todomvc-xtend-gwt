package org.eclipse.xtend.gwt.todomvc.shared;

import java.io.Serializable;

@SuppressWarnings("all")
public class Todo implements Serializable {
  private String _title;
  
  public String getTitle() {
    return this._title;
  }
  
  public void setTitle(final String title) {
    this._title = title;
  }
  
  private boolean _done;
  
  public boolean isDone() {
    return this._done;
  }
  
  public void setDone(final boolean done) {
    this._done = done;
  }
}
