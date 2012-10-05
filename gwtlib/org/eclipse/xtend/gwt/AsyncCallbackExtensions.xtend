package org.eclipse.xtend.gwt

import com.google.gwt.user.client.rpc.AsyncCallback
import static com.google.gwt.core.client.GWT.*

class AsyncCallbackExtensions {
	
	def static <T> DefaultAsyncCallback<T> handle((Result<T>)=>void handler) {
		onFailure(onSuccess([
			handler.apply(new Result<T>(it, null))
		]), [
			handler.apply(new Result<T>(null, it))
		])
	}
	
	def static <T> DefaultAsyncCallback<T> onSuccess((T)=>void handler) {
		new DefaultAsyncCallback<T> => [
			successHandler = handler
		]
	}
	def static <T> DefaultAsyncCallback<T> onFailure((Throwable)=>void handler) {
		new DefaultAsyncCallback<T> => [
			failureHandler = handler
		]
	}
	
	def static <T> DefaultAsyncCallback<T> onFailure(DefaultAsyncCallback<T> callBack, (Throwable)=>void handler) {
		callBack => [
			failureHandler = handler
		]
	}
}

@Data class Result<T> {
	T result
	Throwable exception
}

class DefaultAsyncCallback<T> implements AsyncCallback<T> {
	
	@Property var (T)=>void successHandler
	@Property var (Throwable)=>void failureHandler

	override onFailure(Throwable caught) {
		if (getFailureHandler == null) {
			log(caught.toString)
		}
		getFailureHandler.apply(caught)
	}
	
	override onSuccess(T result) {
		getSuccessHandler?.apply(result)
	}
	
}