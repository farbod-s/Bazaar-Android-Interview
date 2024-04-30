package ir.cafebazaar.network.utils

interface ExceptionHandler {
    fun toHumanReadableMessage(exception: Exception): String
}