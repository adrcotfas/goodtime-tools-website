package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import tools.goodtime.GOOGLE_PLAY_STORE_URL

val FooterStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(Colors.Black)
            .padding(top = 3.cssRem, bottom = 2.cssRem, leftRight = 10.percent)
    }
    Breakpoint.MD {
        Modifier.padding(top = 4.cssRem, bottom = 3.cssRem, leftRight = 15.percent)
    }
}

val FooterLinkStyle = CssStyle.base {
    Modifier
        .color(Colors.White)
        .textDecorationLine(TextDecorationLine.None)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        FooterStyle.toModifier().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.margin(bottom = 2.cssRem)) {
            Link(
                path = GOOGLE_PLAY_STORE_URL,
                openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB
            ) {
                Image(
                    src = "/google_play_badge.webp",
                    description = "Get it on Google Play",
                    modifier = Modifier.height(60.px)
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .gap(1.cssRem),
            horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .gap(1.cssRem),
                horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center
            ) {
                FooterLink("mailto:contact@goodtime.tools", "Contact", true)
                Span(Modifier.color(Colors.Gray).toAttrs()) { Text("|") }
                FooterLink("/legal", "Terms & Privacy Policy")
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .margin(top = 1.cssRem)
                .gap(1.cssRem),
            horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .gap(1.cssRem),
                horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center
            ) {
                FooterLink("https://www.linkedin.com/company/goodtime-app/", "LinkedIn", true)
                Span(Modifier.color(Colors.Gray).toAttrs()) { Text("|") }
                FooterLink("https://github.com/adrcotfas/goodtime", "GitHub", true)
            }
        }

        // Copyright
        Span(
            Modifier
                .textAlign(TextAlign.Center)
                .margin(top = 1.cssRem)
                .fontSize(0.9.cssRem)
                .color(Colors.Gray)
                .toAttrs()
        ) {
            Text("Designed and developed by Adrian Cotfas © 2025")
        }
    }
}


@Composable
private fun FooterLink(href: String, text: String, openNewTab: Boolean = false) {
    val openExternalStrategy = if (openNewTab) OpenLinkStrategy.IN_NEW_TAB else null

    Link(
        href,
        text,
        modifier = FooterLinkStyle.toModifier(),
        variant = UncoloredLinkVariant,
        openExternalLinksStrategy = openExternalStrategy
    )
}