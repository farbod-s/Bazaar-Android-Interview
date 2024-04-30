package ir.cafebazaar.network.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.cafebazaar.network.R
import java.io.IOException
import javax.inject.Inject

class MovieExceptionHandler @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionHandler {

    override fun toHumanReadableMessage(exception: Exception): String {
        return when (exception) {
            is IOException -> context.getString(R.string.error_message_io)
            else -> context.getString(R.string.error_message_generic)
        }
    }
}