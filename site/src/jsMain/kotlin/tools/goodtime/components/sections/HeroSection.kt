package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Video
import tools.goodtime.nonRightClickable

@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .displayUntil(Breakpoint.MD),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TitleSection()

        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            VideoSection()
        }
    }

    Row(
        modifier = Modifier
            .id("download")
            .fillMaxWidth()
            .displayIfAtLeast(Breakpoint.MD),
        horizontalArrangement = Arrangement.spacedBy(10.cssRem, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleSection()
        VideoSection()
    }
}

@Composable
fun TitleSection() {
    Column(
        modifier = Modifier.padding(1.cssRem),
        verticalArrangement = Arrangement.spacedBy(1.5.cssRem, Alignment.CenterVertically),
    ) {

        Row(
            modifier = Modifier.displayUntil(Breakpoint.MD).fillMaxWidth().padding(top = 3.cssRem),
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
}

@Composable
fun VideoSection() {
    Box(
        Modifier.padding(top = 2.cssRem),
        contentAlignment = Alignment.Center
    ) {
        Video(attrs = {
            width(260)
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
        Image(
            src = "/phone.png",
            modifier = Modifier
                .width(340.px)
        )
    }
}

@Composable
fun HeroSectionContent() {
    val title = "Unlock Deep Concentration"
    val titleModifier = Modifier
        .fontFamily("RobotoFlex")
        .fontSize(2.25.cssRem)
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
        .maxWidth(350.px)
        .fontSize(1.15.cssRem)
        .margin(top = 1.cssRem)
        .display(DisplayStyle.Block)

    Span(attrs = subtitleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(subtitle)
    }
    Span(attrs = subtitleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(subtitle)
    }

    Column(
        modifier = Modifier.margin(top = 2.cssRem),
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
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
                "4.6/5 (20,000+ reviews)",
                Modifier
                    .fontSize(1.cssRem)
                    .color(Colors.Gray)
            )
        }
    }
}