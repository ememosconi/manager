package com.company.manager.config.exception

import com.company.manager.config.ErrorCatalog


open class GenericException(
    val errorCode: String = ErrorCatalog.INTERNAL_SERVER_ERROR.name,
    message: String = ErrorCatalog.INTERNAL_SERVER_ERROR.defaultMessage,
    cause: Throwable? = null,
) : RuntimeException(
    message,
    cause
)
