package org.eclipse.xtend.gwt

import com.google.gwt.user.client.Timer

class TimerExtensions {
	def static runEvery(int milliSec, (Void)=>void work) {
		new XtendTimer(work).scheduleRepeating(milliSec)
	}
	def static runIn(int milliSec, (Void)=>void work) {
		new XtendTimer(work).schedule(milliSec)
	}
}

class XtendTimer extends Timer {
	val (Void)=>void work
	new((Void)=>void work) {
		this.work = work
	} 

	override run() {
		work.apply(null)
	}
	
}