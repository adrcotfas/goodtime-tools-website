package tools.goodtime.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.descendants
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import tools.goodtime.FeaturesButton
import tools.goodtime.sectionTitle
import tools.goodtime.widgets.FeatureCard
import tools.goodtime.widgets.FeatureCardRich
import tools.goodtime.widgets.ThemedButton


val FeaturesLinkStyle = CssStyle {
    descendants("a") {
        Modifier.styleModifier {
            property("color", "#50BFACFF")
        }
    }
    descendants("a:visited") {
        Modifier.styleModifier {
            property("color", "#50BFACFF")
        }
    }
}

val ScreenshotsStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .padding(top = 2.cssRem, leftRight = 2.cssRem)
        .overflow { x(Overflow.Auto) }
        .styleModifier {
            property("display", "flex")
            property("gap", "1rem")
            property("flex-shrink", "0")
            property("min-width", "0")
            property("cursor", "grab")
            property("-webkit-overflow-scrolling", "touch")
            property("scrollbar-width", "none")
            property("-ms-overflow-style", "none")
        }
}

@Composable
fun FeaturesSection() {
    var showMore by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = FeaturesLinkStyle.toModifier().id("features")
    ) {
        val title = "Minimalist But Powerful"
        val titleModifier = Modifier
            .sectionTitle()
            .fontSize(2.25.cssRem)

        Span(attrs = titleModifier.textAlign(TextAlign.Center).toAttrs()) {
            Text(title)
        }

        SimpleGrid(
            numColumns(1, md = 3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.cssRem)
                .gap(2.cssRem),
        ) {
            FeatureCard(
                title = "Focus, Not Fluff",
                description = listOf(
                    "Control the timer with swipe gestures.",
                    "No clutter, no gimmicks, no distractions. Just a clean timer that respects your attention, battery, and privacy."
                )
            )
            FeatureCardRich(
                title = "Timer Profiles",
                description = {
                    Span(
                        attrs = Modifier
                            .fontSize(1.cssRem)
                            .color(Colors.Gray).toAttrs()
                    ) {
                        Text("Strict countdown timers for techniques like ")
                        Link(
                            "https://en.wikipedia.org/wiki/Pomodoro_Technique",
                            "Pomodoro",
                            modifier = Modifier.textDecorationLine(TextDecorationLine.Underline)
                        )
                        Text(", or open-ended ")
                        Link(
                            "https://www.lesswrong.com/posts/RWu8eZqbwgB9zaerh/third-time-a-better-way-to-work",
                            "count-ups",
                            modifier = Modifier.textDecorationLine(TextDecorationLine.Underline)
                        )
                        Text(" for deep, uninterrupted flow.")
                    }
                }
            )

            FeatureCard(
                title = "Colored Labels",
                description = listOf(
                    "Organize tasks with colored labels, each with custom timer settings.",
                    "Archive when done."
                )
            )

            if (showMore) {
                FeatureCard(
                    title = "Track Progress",
                    description = listOf(
                        "Detailed stats show your productivity patterns.",
                        "Organize by labels, add notes, and manually log sessions you completed offline."
                    )
                )
                FeatureCard(
                    title = "Timer Customization",
                    description = listOf(
                        "Customize timer size, immersive mode, and indicators.",
                        "Autostart sessions and pick notification styles from sounds to silent flashes."
                    )
                )
                FeatureCard(
                    title = "Backup and Restore",
                    description = listOf(
                        "Automatic local backups plus manual export/import for switching devices.",
                        "Export to CSV or JSON for external analysis."
                    )
                )
                FeatureCard(
                    title = "Build the Habit",
                    description = listOf("Set reminders to keep your productivity routine consistent.")
                )
                FeatureCard(
                    title = "No Interruptions",
                    description = listOf("Enable Do not Disturb Mode to automatically silence notifications while the timer runs.")
                )
                FeatureCard(
                    title = "Open Source",
                    description = listOf("Indie-made with no ads or tracking. Open source so you can verify, contribute, or learn.")
                )
            }
        }
        if (!showMore) {
            ThemedButton(
                text = "Show More",
                onClick = {
                    showMore = true
                },
                primary = false,
                modifier = FeaturesButton.toModifier(),
            )
        }
        Box(modifier = Modifier.fillMaxWidth().overflow { x(Overflow.Hidden) }) {
            Screenshots()
        }
    }
}

@Composable
private fun Screenshots() {
    val screenshots = remember {
        listOf(
            "main_fullscreen.webp",
            "main.webp",
            "main_dial.webp",
            "label_list.webp",
            "label_detail.webp",
            "stats_top.webp",
            "stats_bar_chart.webp",
            "stats_bottom.webp",
            "settings_top.webp",
            "settings_bottom.webp",
            "lock_screen.webp",
        )
    }

    Div(
        attrs = ScreenshotsStyle.toModifier()
            .toAttrs {
                attr("data-drag-scroll", "")
            }
    ) {
        screenshots.forEach { screenshot ->
            Image(
                src = "/screenshots/$screenshot",
                modifier = Modifier
                    .height(400.px)
                    .borderRadius(1.cssRem)
                    .userSelect(UserSelect.None)
            )
        }
    }
}
