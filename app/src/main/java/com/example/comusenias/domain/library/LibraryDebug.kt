package com.example.comusenias.domain.library

import android.os.Debug

object LibraryDebug {


    fun printLog(message: String) {
        println(message)
    }

    fun appIsDebuggable(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }

    /* ------------- Útiles --------------------------------------------------------------------- */
    fun setAppForTesting() {
        this.TESTING_FLAG = true
    }


    fun isTesteable(): Boolean? {
        return this.TESTING_FLAG
    }

    private var TESTING_FLAG = false

}