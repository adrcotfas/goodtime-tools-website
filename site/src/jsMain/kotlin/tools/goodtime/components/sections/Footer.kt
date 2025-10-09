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
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
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
        .margin(bottom = 0.5.cssRem)
        .textDecorationLine(TextDecorationLine.Underline)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        FooterStyle.toModifier().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Link(
            GOOGLE_PLAY_STORE_URL,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB
        ) {
            Image(
                src = "/google_play_badge.webp",
                description = "Get it on Google Play",
                modifier = Modifier.height(60.px).margin(bottom = 2.cssRem)
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .margin(bottom = 2.cssRem)
                .gap(1.cssRem),
            horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .margin(bottom = 7.px)
                .gap(1.cssRem),
                horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.Center) {
                Link(
                    "mailto:contact@goodtime.tools",
                    modifier = Modifier.color(Colors.White).textDecorationLine(TextDecorationLine.None),
                    openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB
                ) {
                    SpanText("Contact")
                }

                Span(Modifier.color(Colors.Gray).toAttrs()) { Text("|") }

                Link(
                    "https://www.linkedin.com/company/goodtime-app/",
                    modifier = Modifier.color(Colors.White).textDecorationLine(TextDecorationLine.None),
                    openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB
                ) {
                    SpanText("LinkedIn")
                }

                Span(Modifier.color(Colors.Gray).toAttrs()) { Text("|") }
            }

            A(
                attrs = Modifier.classNames("github-button").toAttrs {
                    attr("href", "https://github.com/adrcotfas/goodtime")
                    attr("data-color-scheme", "no-preference: light; light: light; dark: dark;")
                    attr("data-size", "large")
                    attr("data-show-count", "true")
                    attr("aria-label", "Star adrcotfas/goodtime on GitHub")
                }
            ) { Text("Star") }
        }

        // Copyright
        Span(
            Modifier
                .textAlign(TextAlign.Center)
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
    val linkModifier = Modifier
        .color(Colors.White)
        .textDecorationLine(TextDecorationLine.None)

    val openExternalStrategy = if (openNewTab) OpenLinkStrategy.IN_NEW_TAB else null

    Link(
        href,
        text,
        linkModifier,
        variant = UncoloredLinkVariant,
        openExternalLinksStrategy = openExternalStrategy
    )
}
