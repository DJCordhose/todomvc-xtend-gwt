package org.eclipse.xtend.gwt;

import org.eclipse.xtend.gwt.DefaultAsyncCallback;
import org.eclipse.xtend.gwt.Result;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class AsyncCallbackExtensions {
  public static <T extends Object> DefaultAsyncCallback<T> handle(final Procedure1<? super Result<T>> handler) {
    final Procedure1<T> _function = new Procedure1<T>() {
        public void apply(final T it) {
          Result<T> _result = new Result<T>(it, null);
          handler.apply(_result);
        }
      };
    DefaultAsyncCallback<T> _onSuccess = AsyncCallbackExtensions.<T>onSuccess(_function);
    final Procedure1<Throwable> _function_1 = new Procedure1<Throwable>() {
        public void apply(final Throwable it) {
          Result<T> _result = new Result<T>(null, it);
          handler.apply(_result);
        }
      };
    DefaultAsyncCallback<T> _onFailure = AsyncCallbackExtensions.<T>onFailure(_onSuccess, _function_1);
    return _onFailure;
  }
  
  public static <T extends Object> DefaultAsyncCallback<T> onSuccess(final Procedure1<? super T> handler) {
    DefaultAsyncCallback<T> _defaultAsyncCallback = new DefaultAsyncCallback<T>();
    final Procedure1<DefaultAsyncCallback<T>> _function = new Procedure1<DefaultAsyncCallback<T>>() {
        public void apply(final DefaultAsyncCallback<T> it) {
          it.setSuccessHandler(handler);
        }
      };
    DefaultAsyncCallback<T> _doubleArrow = ObjectExtensions.<DefaultAsyncCallback<T>>operator_doubleArrow(_defaultAsyncCallback, _function);
    return _doubleArrow;
  }
  
  public static <T extends Object> DefaultAsyncCallback<T> onFailure(final Procedure1<? super Throwable> handler) {
    DefaultAsyncCallback<T> _defaultAsyncCallback = new DefaultAsyncCallback<T>();
    final Procedure1<DefaultAsyncCallback<T>> _function = new Procedure1<DefaultAsyncCallback<T>>() {
        public void apply(final DefaultAsyncCallback<T> it) {
          it.setFailureHandler(handler);
        }
      };
    DefaultAsyncCallback<T> _doubleArrow = ObjectExtensions.<DefaultAsyncCallback<T>>operator_doubleArrow(_defaultAsyncCallback, _function);
    return _doubleArrow;
  }
  
  public static <T extends Object> DefaultAsyncCallback<T> onFailure(final DefaultAsyncCallback<T> callBack, final Procedure1<? super Throwable> handler) {
    final Procedure1<DefaultAsyncCallback<T>> _function = new Procedure1<DefaultAsyncCallback<T>>() {
        public void apply(final DefaultAsyncCallback<T> it) {
          it.setFailureHandler(handler);
        }
      };
    DefaultAsyncCallback<T> _doubleArrow = ObjectExtensions.<DefaultAsyncCallback<T>>operator_doubleArrow(callBack, _function);
    return _doubleArrow;
  }
}
