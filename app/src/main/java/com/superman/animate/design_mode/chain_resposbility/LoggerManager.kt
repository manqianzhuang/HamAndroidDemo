package com.superman.animate.design_mode.chain_resposbility

object LoggerManager {

    fun getChainOfLogger(): BaseLogger {
        val debugLogger = DebugLogger(BaseLogger.debug)
        val infoLogger = InfoLogger(BaseLogger.info)
        val errorLogger = ErrorLogger(BaseLogger.error)

        infoLogger.setNextChain(errorLogger)
        debugLogger.setNextChain(infoLogger)
        return debugLogger
    }


}