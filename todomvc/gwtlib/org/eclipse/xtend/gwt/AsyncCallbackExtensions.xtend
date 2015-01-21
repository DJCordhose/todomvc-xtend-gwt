package org.eclipse.xtend.gwt

import com.google.gwt.user.client.rpc.AsyncCallback

import static com.google.gwt.core.client.GWT.*

class AsyncCallbackExtensions {
	
	def static <T> AsyncCallback<T> onSuccess((T)=>void handler) {
		new AsyncCallback<T>() {

			override onSuccess(T result) {
				handler.apply(result)
			}
			
			override onFailure(Throwable caught) {
				log(caught.toString)
			}
			
		} 
	}
	
}
