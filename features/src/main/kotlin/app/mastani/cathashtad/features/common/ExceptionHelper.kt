package app.mastani.cathashtad.features.common

import java.io.IOException

open class HttpException(message: String) : IOException(message)
class NetworkException(override val message: String) : HttpException(message)
class NotFoundException(override val message: String) : HttpException(message)
class UnauthorizedException(override val message: String) : HttpException(message)
class ForbiddenException(override val message: String) : HttpException(message)
class BadRequestException(override val message: String) : HttpException(message)
class TooManyRequestException(override val message: String) : HttpException(message)
class InternalServerException(override val message: String) : HttpException(message)
class UnknownException(override val message: String) : HttpException(message)
class NoConnectivityException(override val message: String) : HttpException(message)
class TimeoutException(override val message: String) : HttpException(message)

suspend fun handleHttpException(
    exception: Throwable,
    onUnauthorizedException: suspend () -> Unit,
    onOtherException: suspend (String?) -> Unit
) {
    when (exception) {
        is UnauthorizedException -> {
            onUnauthorizedException()
        }
        else -> {
            onOtherException(exception.message)
        }
    }
}