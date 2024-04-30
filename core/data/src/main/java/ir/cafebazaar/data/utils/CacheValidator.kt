package ir.cafebazaar.data.utils

interface CacheValidator {
    suspend fun isValid(): Boolean
}