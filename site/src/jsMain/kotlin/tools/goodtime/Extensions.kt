package tools.goodtime

import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.Color
import org.w3c.dom.Element

fun <T : Element> AttrsScope<T>.nonRightClickable() {
    onContextMenu { event ->
        event.preventDefault()
    }
}

fun Modifier.gradientText() = Modifier.backgroundImage(linearGradient {
    add(Color("#fff"))
    add(Color("#fff9"))
})
    .color(Colors.Transparent)
    .styleModifier {
        property("background-clip", "text")
        property("-webkit-background-clip", "text") // for webkit browsers
    }