package tools.goodtime.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.Element
import tools.goodtime.components.layouts.PageLayoutData
import tools.goodtime.components.sections.HomeLogo
import tools.goodtime.nonRightClickable
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

        // Features Section
        //FeaturesSection()

        // Reviews Section
        //ReviewsSection()
    }
}

@Composable
private fun HeroSection() {

    SimpleGrid(
        numColumns(1, md = 2),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(1.cssRem).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(1.5.cssRem, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier.displayUntil(Breakpoint.MD).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HomeLogo()
            }

            Column(
                modifier = Modifier.displayIfAtLeast(Breakpoint.MD).fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                HeroSectionContent()
            }

            Column(
                modifier = Modifier.displayUntil(Breakpoint.MD).fillMaxWidth()
                    .padding(top = 2.cssRem, bottom = 1.cssRem),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeroSectionContent()
            }
        }

        Column(
            modifier = Modifier.padding(1.cssRem),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.padding(top = 2.cssRem),
                contentAlignment = Alignment.Center
            ) {
                Video(attrs = {
                    width(320)
                    attr("autoplay", "")
                    attr("playsinline", "")
                    attr("preload", "auto")
                    attr("loop", "")
                    attr("muted", "")
                    nonRightClickable()
                }) {
                    Source(attrs = {
                        attr("src", "videos/demo.mp4")
                        attr("type", "video/mp4")
                    })
                }
            }
        }
    }
}

@Composable
fun HeroSectionContent() {
    val title = "Unlock Deep Concentration"
    val titleModifier = Modifier
        .fontFamily("RobotoFlex")
        .fontSize(2.5.cssRem)
        .lineHeight(1.15)
        .fontWeight(700)
        .display(DisplayStyle.Block)

    Span(attrs = titleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(title)
    }

    Span(attrs = titleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(title)
    }

    val subtitle = "Join other learners, creators, and leaders who are getting things done"
    val subtitleModifier = Modifier
        .width(350.px)
        .fontSize(1.15.cssRem)
        .margin(bottom = 0.5.cssRem)
        .display(DisplayStyle.Block)

    Span(attrs = subtitleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(subtitle)
    }
    Span(attrs = subtitleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(subtitle)
    }

    Column(
        modifier = Modifier.margin(top = 2.cssRem),
        verticalArrangement = Arrangement.spacedBy(1.5.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Anchor(href = "https://play.google.com/store/apps/details?id=com.apps.adrcotfas.goodtime") {
            Image(
                "/google_play_badge.png",
                "Get it on Google Play",
                Modifier.display(DisplayStyle.Block)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.5.cssRem, alignment = Alignment.CenterHorizontally)
        ) {
            SpanText(
                "★★★★★",
                Modifier
                    .fontSize(1.25.cssRem)
                    .color(Colors.PaleGoldenRod)
            )
            SpanText(
                "4.6/5 (20k+ reviews)",
                Modifier
                    .fontSize(1.cssRem)
                    .color(Colors.Gray)
            )
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
private fun ReviewsSection() {
    Div(
        Modifier
            .id("reviews")
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

            // Placeholder for reviews
            SpanText(
                "Reviews coming soon...",
                Modifier
                    .fontSize(1.cssRem)
                    .color(Colors.Gray)
                    .fontStyle(FontStyle.Italic)
            )
        }
    }
}


