package ir.cafebazaar.tmdb.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.cafebazaar.tmdb.R

@Composable
fun DefaultErrorView(errorMessage: String, retry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dissatisfied),
                contentDescription = "Error Icon",
                modifier = Modifier
                    .size(96.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF092040), Color(0xFF031329)),
                            startY = 0.0f,
                            endY = Float.POSITIVE_INFINITY
                        ),
                        shape = CircleShape
                    )
                    .padding(24.dp),
                tint = Color(0xFF8290B4)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Connection glitch",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF7E91B7)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = retry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C3051),
                    contentColor = Color.White,
                )
            ) {
                Text("Retry")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDefaultErrorView() {
    DefaultErrorView("Seems like there's an internet connection problem.") {}
}