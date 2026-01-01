package tools.goodtime.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext

@Page
@Composable
fun NotFoundPage() {
    val ctx = rememberPageContext()
    LaunchedEffect(Unit) {
        ctx.router.navigateTo("/")
    }
}