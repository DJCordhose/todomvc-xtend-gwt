package org.eclipse.xtend.gwt;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

@Data
@SuppressWarnings("all")
public class Result<T extends Object> {
  private final T _result;
  
  public T getResult() {
    return this._result;
  }
  
  private final Throwable _exception;
  
  public Throwable getException() {
    return this._exception;
  }
  
  public Result(final T result, final Throwable exception) {
    super();
    this._result = result;
    this._exception = exception;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_result== null) ? 0 : _result.hashCode());
    result = prime * result + ((_exception== null) ? 0 : _exception.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Result other = (Result) obj;
    if (_result == null) {
      if (other._result != null)
        return false;
    } else if (!_result.equals(other._result))
      return false;
    if (_exception == null) {
      if (other._exception != null)
        return false;
    } else if (!_exception.equals(other._exception))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
}
