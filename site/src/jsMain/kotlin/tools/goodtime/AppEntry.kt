package tools.goodtime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.saveToLocalStorage
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.events.MouseEvent

private const val COLOR_MODE_KEY = "goodtime:colorMode"

private fun setupDragScroll() {
    val containers = document.querySelectorAll("[data-drag-scroll]")

    for (i in 0 until containers.length) {
        val element = containers.item(i) as? HTMLElement ?: continue
        var isDown = false
        var startX = 0.0
        var scrollLeft = 0.0
        var offsetLeft = 0.0

        // Prevent default drag on images inside container
        element.addEventListener("dragstart", { e ->
            e.preventDefault()
        })

        element.addEventListener("mousedown", { e ->
            val event = e as MouseEvent
            isDown = true
            element.style.cursor = "grabbing"
            offsetLeft = element.offsetLeft.toDouble()
            startX = event.pageX - offsetLeft
            scrollLeft = element.scrollLeft
        })

        element.addEventListener("mouseleave", {
            isDown = false
            element.style.cursor = "grab"
        })

        element.addEventListener("mouseup", {
            isDown = false
            element.style.cursor = "grab"
        })

        element.addEventListener("mousemove", { e ->
            if (!isDown) return@addEventListener
            val event = e as MouseEvent
            e.preventDefault()
            // Use cached offsetLeft instead of querying element.offsetLeft
            val x = event.pageX - offsetLeft
            val walk = (x - startX) * 2 // Scroll speed multiplier
            element.scrollLeft = scrollLeft - walk
        })
    }
}

@InitSilk
fun initColorMode(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.loadFromLocalStorage(COLOR_MODE_KEY) ?: ColorMode.systemPreference
}

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.apply {
        registerStyleBase("html, body") {
            Modifier.fillMaxHeight().styleModifier {
                property("overscroll-behavior", "none")
            }
        }
        registerStyleBase("[data-drag-scroll]::-webkit-scrollbar") {
            Modifier.styleModifier {
                property("display", "none")
            }
        }
    }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        val colorMode = ColorMode.current
        LaunchedEffect(colorMode) {
            colorMode.saveToLocalStorage(COLOR_MODE_KEY)
        }

        LaunchedEffect(Unit) {
            // Add favicon
            val favicon = document.createElement("link") as HTMLLinkElement
            favicon.rel = "icon"
            favicon.type = "image/png"
            favicon.href = "/favicon.png"
            document.head?.appendChild(favicon)

            // Add drag scroll functionality
            setupDragScroll()
        }

        Surface(SmoothColorStyle.toModifier().fillMaxHeight()) {
            content()
        }
    }
}
