package tools.goodtime.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.compose.css.TextAlign
import tools.goodtime.HeadlineTextStyle
import tools.goodtime.SubheadlineTextStyle
import tools.goodtime.components.layouts.PageLayoutData
import tools.goodtime.toSitePalette

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

private val GridCellColorVar by StyleVariable<Color>()
val HomeGridCellStyle = CssStyle.base {
    Modifier
        .backgroundColor(GridCellColorVar.value())
        .boxShadow(blurRadius = 0.6.cssRem, color = GridCellColorVar.value())
        .borderRadius(1.cssRem)
}

@Composable
private fun GridCell(color: Color, row: Int, column: Int, width: Int? = null, height: Int? = null) {
    Div(
        HomeGridCellStyle.toModifier()
            .setVariable(GridCellColorVar, color)
            .gridItem(row, column, width, height)
            .toAttrs()
    )
}


@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    Column(Modifier.fillMaxWidth()) {
        // Hero Section
        HeroSection()

        // Download Section
        DownloadSection()

        // Features Section
        FeaturesSection()

        // Testimonials Section
        TestimonialsSection()
    }
}

@Composable
private fun HeroSection() {
    val sitePalette = ColorMode.current.toSitePalette()

    Div(
        Modifier
            .fillMaxWidth()
            .minHeight(80.vh)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .padding(2.cssRem)
            .toAttrs()
    ) {
        Div(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            SpanText(
                "Welcome to Goodtime Productivity",
                Modifier
                    .fontSize(3.cssRem)
                    .fontWeight(700)
                    .color(sitePalette.brand.primary)
                    .margin(bottom = 1.cssRem)
                    .display(DisplayStyle.Block)
            )

            SpanText(
                "Boost your productivity with our innovative tools and features designed to help you work smarter, not harder.",
                Modifier
                    .fontSize(1.2.cssRem)
                    .color(Colors.Gray)
                    .maxWidth(40.cssRem)
                    .margin(bottom = 2.cssRem)
                    .display(DisplayStyle.Block)
            )

            Button(
                onClick = { /* TODO: Scroll to download section */ },
                colorPalette = ColorPalettes.Blue,
                modifier = Modifier.fontSize(1.1.cssRem).padding(1.cssRem, 2.cssRem)
            ) {
                Text("Get Started")
            }
        }
    }
}

@Composable
private fun DownloadSection() {
    Div(
        Modifier
            .id("download")
            .fillMaxWidth()
            .minHeight(60.vh)
            .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .padding(2.cssRem)
            .toAttrs()
    ) {
        Div(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            SpanText(
                "Download Now",
                Modifier
                    .fontSize(2.5.cssRem)
                    .fontWeight(700)
                    .color(ColorMode.current.toSitePalette().brand.primary)
                    .margin(bottom = 1.cssRem)
                    .display(DisplayStyle.Block)
            )

            SpanText(
                "Get the latest version of our productivity tools for Windows, macOS, and Linux.",
                Modifier
                    .fontSize(1.1.cssRem)
                    .color(Colors.Gray)
                    .maxWidth(35.cssRem)
                    .margin(bottom = 2.cssRem)
                    .display(DisplayStyle.Block)
            )

            Button(
                onClick = { /* TODO: Download action */ },
                colorPalette = ColorPalettes.Green,
                modifier = Modifier.fontSize(1.1.cssRem).padding(1.cssRem, 2.cssRem)
            ) {
                Text("Download for Free")
            }
        }
    }
}

@Composable
private fun FeaturesSection() {
    Div(
        Modifier
            .id("features")
            .fillMaxWidth()
            .minHeight(60.vh)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .padding(2.cssRem)
            .toAttrs()
    ) {
        Div(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            SpanText(
                "Powerful Features",
                Modifier
                    .fontSize(2.5.cssRem)
                    .fontWeight(700)
                    .color(ColorMode.current.toSitePalette().brand.primary)
                    .margin(bottom = 1.cssRem)
                    .display(DisplayStyle.Block)
            )

            SpanText(
                "Discover the features that make our productivity tools stand out from the competition.",
                Modifier
                    .fontSize(1.1.cssRem)
                    .color(Colors.Gray)
                    .maxWidth(35.cssRem)
                    .margin(bottom = 3.cssRem)
                    .display(DisplayStyle.Block)
            )

            // Placeholder for feature items
            SpanText(
                "Feature showcase coming soon...",
                Modifier
                    .fontSize(1.cssRem)
                    .color(Colors.Gray)
                    .fontStyle(FontStyle.Italic)
            )
        }
    }
}

@Composable
private fun TestimonialsSection() {
    Div(
        Modifier
            .id("testimonials")
            .fillMaxWidth()
            .minHeight(60.vh)
            .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .padding(2.cssRem)
            .toAttrs()
    ) {
        Div(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            SpanText(
                "What Our Users Say",
                Modifier
                    .fontSize(2.5.cssRem)
                    .fontWeight(700)
                    .color(ColorMode.current.toSitePalette().brand.primary)
                    .margin(bottom = 1.cssRem)
                    .display(DisplayStyle.Block)
            )

            SpanText(
                "See how our tools have helped users improve their productivity and workflow.",
                Modifier
                    .fontSize(1.1.cssRem)
                    .color(Colors.Gray)
                    .maxWidth(35.cssRem)
                    .margin(bottom = 3.cssRem)
                    .display(DisplayStyle.Block)
            )

            // Placeholder for testimonials
            SpanText(
                "Testimonials coming soon...",
                Modifier
                    .fontSize(1.cssRem)
                    .color(Colors.Gray)
                    .fontStyle(FontStyle.Italic)
            )
        }
    }
}
