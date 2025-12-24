package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
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
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.HTMLVideoElement
import tools.goodtime.GOOGLE_PLAY_STORE_URL
import tools.goodtime.HeroButton
import tools.goodtime.gradientText
import tools.goodtime.sectionTitle
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
    var showPlayButton by remember { mutableStateOf(false) }
    var videoElement by remember { mutableStateOf<HTMLVideoElement?>(null) }

    Box(
        Modifier.padding(top = 2.cssRem),
        contentAlignment = Alignment.Center
    ) {
        Video(attrs = {
            width(270)
            attr("autoplay", "")
            attr("muted", "true")
            attr("playsinline", "")
            attr("preload", "auto")
            attr("loop", "")
            attr("webkit-playsinline", "")
            attr("x5-playsinline", "")
            attr("x5-video-player-type", "h5")
            attr("x5-video-player-fullscreen", "false")
            attr("poster", "/videos/demo-fallback.webp")
            nonRightClickable()
            ref { video ->
                videoElement = video

                // Listen to video events to track actual playback state
                val onPlaying = { _: dynamic ->
                    console.log("Video is playing")
                    showPlayButton = false
                }
                val onPause = { _: dynamic ->
                    console.log("Video paused")
                    showPlayButton = true
                }

                video.addEventListener("playing", onPlaying)
                video.addEventListener("pause", onPause)

                // Try to autoplay - if it fails, show the play button
                video.play().catch { error ->
                    console.log("Video autoplay blocked, showing play button:", error)
                    showPlayButton = true
                }

                onDispose {
                    video.removeEventListener("playing", onPlaying)
                    video.removeEventListener("pause", onPause)
                }
            }
        }) {
            Source(attrs = {
                attr("src", "videos/demo.mp4")
                attr("type", "video/mp4")
            })
        }

        Img(
            src = "/phone.webp",
            attrs = Modifier
                .width(350.px)
                .toAttrs {
                    attr("fetchpriority", "high")
                    attr("loading", "eager")
                    style {
                        property("pointer-events", "none")
                    }
                    nonRightClickable()
                }
        )

        // Play button overlay - only shown when video is not playing
        if (showPlayButton) {
            Box(
                modifier = Modifier
                    .size(80.px)
                    .borderRadius(50.percent)
                    .backgroundColor(rgba(0, 0, 0, 0.7))
                    .cursor(Cursor.Pointer)
                    .zIndex(10)
                    .onClick {
                        videoElement?.play()?.catch { error ->
                            console.log("Failed to play video:", error)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                // Play icon triangle
                Box(
                    modifier = Modifier
                        .margin(left = 6.px)
                        .size(0.px)
                        .borderLeft(20.px, LineStyle.Solid, Colors.White)
                        .borderTop(15.px, LineStyle.Solid, Colors.Transparent)
                        .borderBottom(15.px, LineStyle.Solid, Colors.Transparent)
                )
            }
        }
    }
}

@Composable
fun HeroSectionContent() {
    val title = "Enter Deep Concentration"
    val titleModifier = Modifier
        .sectionTitle()
        .fontSize(3.25.cssRem)

    Span(attrs = titleModifier.displayUntil(Breakpoint.MD).textAlign(TextAlign.Center).toAttrs()) {
        Text(title)
    }

    Span(attrs = titleModifier.displayIfAtLeast(Breakpoint.MD).textAlign(TextAlign.Start).toAttrs()) {
        Text(title)
    }

    val subtitle = "Join other learners, creators, and leaders who are getting things done."
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

    SimpleGrid(
        numColumns(1, md = 2),
        Modifier.margin(topBottom = 2.cssRem).rowGap(1.cssRem).columnGap(1.cssRem),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(1.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinkButton(
                GOOGLE_PLAY_STORE_URL,
                HeroButton.toModifier(),
                "Android",
                primary = true,
                shape = ButtonShape.ROUNDED_RECTANGLE
            )
            RatingBrag(Modifier.padding(left = 1.cssRem, right = 1.cssRem))
        }
        Box(
            modifier = Modifier.height(3.5.cssRem).minWidth(160.px).padding(8.px).borderRadius(32.px)
                .border(width = 1.px, color = Colors.DimGray, style = LineStyle.Solid),
            contentAlignment = Alignment.Center
        ) {
            SpanText(text = "soon on iOS", modifier = Modifier.color(Colors.White))
        }
    }
}

val RatingTextStyle = CssStyle.base {
    Modifier
        .gradientText()
        .fontSize(1.25.cssRem)
        .padding(right = 0.25.cssRem)
        .fontWeight(700)
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
                "4.7",
                RatingTextStyle.toModifier()
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
            "21,000+ reviews",
            Modifier
                .fontSize(0.85.cssRem)
                .fontWeight(600)
                .color(Colors.DarkGray)
        )
    }
}
