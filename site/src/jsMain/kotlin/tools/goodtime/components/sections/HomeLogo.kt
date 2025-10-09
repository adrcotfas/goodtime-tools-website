package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem

@Composable
fun HomeLogo() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(
            0.5.cssRem,
            Alignment.Start
        )
    ) {
        Image(src = "goodtime_logo.webp", modifier = Modifier.size(2.5.cssRem))
        Row {
            SpanText(
                text = "Goodtime", modifier = Modifier
                    .fontSize(1.3.cssRem)
                    .fontWeight(500)
                    .display(DisplayStyle.Block)
            )
            SpanText(
                text = " Productivity", modifier = Modifier
                    .fontSize(1.3.cssRem)
                    .fontWeight(200)
                    .display(DisplayStyle.Block)
            )
        }
    }
}