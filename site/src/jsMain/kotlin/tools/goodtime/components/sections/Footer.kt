package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Span
import tools.goodtime.toSitePalette

val FooterStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .padding(top = 3.cssRem, bottom = 2.cssRem, leftRight = 10.percent)
    }
    Breakpoint.MD {
        Modifier.padding(top = 4.cssRem, bottom = 3.cssRem, leftRight = 15.percent)
    }
}

val FooterLinkStyle = CssStyle.base {
    Modifier
        .margin(bottom = 0.5.cssRem)
        .textDecorationLine(TextDecorationLine.Underline)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    val sitePalette = ColorMode.current.toSitePalette()

    Column(
        FooterStyle.toModifier().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top line with links
        Row(
            Modifier
                .fillMaxWidth()
                .margin(bottom = 1.cssRem),
            horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.SpaceEvenly
        ) {
            FooterLink("/terms", "Terms and Privacy")
            FooterLink("https://github.com/adrcotfas/goodtime-tools-website/discussions", "Discussions", openNewTab = true)
            FooterLink("mailto:contact@goodtime.tools", "Contact")
        }

        // Bottom attribution row
        Span(
            Modifier
                .textAlign(TextAlign.Center)
                .fontSize(0.9.cssRem)
                .color(sitePalette.brand.primary)
                .toAttrs()
        ) {
            SpanText("Designed and developed by Adrian Cotfas")
        }
    }
}



@Composable
private fun FooterLink(href: String, text: String, openNewTab: Boolean = false) {
    val linkModifier = FooterLinkStyle.toModifier()
        .setVariable(ColorVar, ColorMode.current.toSitePalette().brand.accent)

    val openExternalStrategy = if (openNewTab) OpenLinkStrategy.IN_NEW_TAB else null

    Link(
        href,
        text,
        linkModifier,
        variant = UncoloredLinkVariant,
        openExternalLinksStrategy = openExternalStrategy
    )
}
