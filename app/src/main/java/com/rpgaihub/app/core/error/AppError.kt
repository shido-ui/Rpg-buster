package com.rpgaihub.app.core.error

/**
 * Standardized application error hierarchy.
 *
 * Future subsystems (AI providers, local database, network services, lore engines)
 * can extend or utilize these variants without requiring architectural alterations.
 */
sealed class AppError(
    open val message: String,
    open val cause: Throwable? = null
) {

    data class NetworkError(
        override val message: String,
        val statusCode: Int? = null,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    data class AiError(
        override val message: String,
        val providerName: String? = null,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    data class DatabaseError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    data class SerializationError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    data class SystemError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    data class UnknownError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)
}
