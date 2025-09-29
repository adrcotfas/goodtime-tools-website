package tools.goodtime

import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.Element

fun <T : Element> AttrsScope<T>.nonRightClickable() {
    onContextMenu { event ->
        event.preventDefault()
    }
}