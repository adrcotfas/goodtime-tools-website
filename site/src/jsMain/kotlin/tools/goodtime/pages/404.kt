package tools.goodtime.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun NotFoundPage() {
    val ctx = rememberPageContext()

    // Your 404 UI here - simple example:
    Text("Page not found: ${ctx.route.path}")

    // Or redirect to home after showing message:
    // LaunchedEffect(Unit) {
    //     delay(2000)
    //     ctx.router.navigateTo("/")
    // }
}