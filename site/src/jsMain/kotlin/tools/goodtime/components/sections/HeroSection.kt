package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Video
import tools.goodtime.GOOGLE_PLAY_STORE_URL
import tools.goodtime.gradientText
import tools.goodtime.nonRightClickable
import tools.goodtime.widgets.ButtonShape
import tools.goodtime.widgets.GradientBox
import tools.goodtime.widgets.GradientBoxVariant70
import tools.goodtime.widgets.LinkButton

@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .displayUntil((Breakpoint.MD))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TitleSection()

        }
        GradientBox(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center, variant = GradientBoxVariant70) {
            VideoSection()
        }
    }

    GradientBox(
        modifier = Modifier.fillMaxWidth().displayIfAtLeast(Breakpoint.MD),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .id("download")
                .fillMaxWidth()
                .padding(top = 6.cssRem),
            horizontalArrangement = Arrangement.spacedBy(3.cssRem, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VideoSection()
            TitleSection()
        }
    }
}

@Composable
fun TitleSection() {

    Column(modifier = Modifier.padding(1.cssRem), horizontalAlignment = Alignment.Start) {
        Column(
            modifier = Modifier.padding(top = 1.cssRem),
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
        .gradientText()
        .fontFamily("RobotoFlex")
        .fontSize(3.25.cssRem)
        .lineHeight(1.15)
        .margin(bottom = 1.cssRem)
        .fontWeight(700)
        .display(DisplayStyle.Block)

    Span(attrs = titleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(title)
    }

    Span(attrs = titleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(title)
    }

    val subtitle = "Timebox for structure or count-up for flow."
    val subtitle2 = "Join other learners, creators, and leaders who are getting things done."
    val subtitleModifier = Modifier
        //.maxWidth(350.px)
        .fontSize(1.15.cssRem)
        .color(Color("#ffffffb3"))
        .display(DisplayStyle.Block)

    Span(attrs = subtitleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(subtitle)
    }
    Span(attrs = subtitleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(subtitle)
    }
    Span(attrs = subtitleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(subtitle2)
    }
    Span(attrs = subtitleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(subtitle2)
    }

    Column(
        modifier = Modifier.margin(top = 2.cssRem),
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinkButton(
            GOOGLE_PLAY_STORE_URL,
            HeroButton.toModifier(),
            "Android",
            primary = true,
            shape = ButtonShape.CIRCLE
        )
        RatingBrag(Modifier.padding(left = 1.cssRem, right = 1.cssRem))
    }
}

@Composable
fun RatingBrag(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.px, alignment = Alignment.CenterHorizontally)
        ) {
            SpanText(
                "4.6",
                Modifier
                    .gradientText()
                    .fontSize(1.25.cssRem)
                    .padding(right = 0.25.cssRem)
                    .fontWeight(700)
            )
            repeat(4) {
                Image(
                    "/star.svg",
                    Modifier.width(20.px)
                )
            }
            Image(
                "/star_partial.svg",
                Modifier.width(20.px)
            )
        }

        SpanText(
            "20,000+ reviews",
            Modifier
                .fontSize(0.85.cssRem)
                .fontWeight(600)
                .color(Colors.DarkGray)
        )
    }
}