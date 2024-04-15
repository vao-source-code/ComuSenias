package com.ars.comusenias.domain.library

import android.os.Debug

object LibraryDebugger {

    fun appIsDebuggable(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
}