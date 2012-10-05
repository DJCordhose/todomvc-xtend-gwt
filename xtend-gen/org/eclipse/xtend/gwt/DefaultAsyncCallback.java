package org.eclipse.xtend.gwt;

import com.google.common.base.Objects;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DefaultAsyncCallback<T extends Object> implements AsyncCallback<T> {
  private Procedure1<? super T> _successHandler;
  
  public Procedure1<? super T> getSuccessHandler() {
    return this._successHandler;
  }
  
  public void setSuccessHandler(final Procedure1<? super T> successHandler) {
    this._successHandler = successHandler;
  }
  
  private Procedure1<? super Throwable> _failureHandler;
  
  public Procedure1<? super Throwable> getFailureHandler() {
    return this._failureHandler;
  }
  
  public void setFailureHandler(final Procedure1<? super Throwable> failureHandler) {
    this._failureHandler = failureHandler;
  }
  
  public void onFailure(final Throwable caught) {
    Procedure1<? super Throwable> _failureHandler = this.getFailureHandler();
    boolean _equals = Objects.equal(_failureHandler, null);
    if (_equals) {
      String _string = caught.toString();
      GWT.log(_string);
    }
    Procedure1<? super Throwable> _failureHandler_1 = this.getFailureHandler();
    _failureHandler_1.apply(caught);
  }
  
  public void onSuccess(final T result) {
    Procedure1<? super T> _successHandler = this.getSuccessHandler();
    if (_successHandler!=null) _successHandler.apply(result);
  }
}
