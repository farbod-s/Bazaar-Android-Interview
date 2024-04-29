package ir.cafebazaar.network.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZTE5OGUwYzIwNzM4ZjcwYmI5NjBkODYxZTMwNjUwNyIsInN1YiI6IjY2MmJkZDM4ZjkxODNhMDExZjMxNGQ2MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xkhE3jJAIteuK5NopuvMgHeAIMlg8EdeY1ow8nKQHxI"
    }
}