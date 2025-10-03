package tools.goodtime.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.descendants
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import tools.goodtime.gradientText
import tools.goodtime.widgets.FeatureCard
import tools.goodtime.widgets.FeatureCardRich
import tools.goodtime.widgets.ThemedButton

val FeaturesButton = CssStyle {
    base {
        // Extra height helps these hero buttons feel a bit more substantial
        Modifier.width(300.px).setVariable(ButtonVars.Height, 2.5.cssRem)
    }

    Breakpoint.MD {
        Modifier.width(150.px)
    }
}

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

@Composable
fun FeaturesSection() {
    var showMore by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = FeaturesLinkStyle.toModifier()
    ) {
        val title = "Minimalist but powerful"
        val titleModifier = Modifier
            .gradientText()
            .fontFamily("RobotoFlex")
            .fontSize(2.25.cssRem)
            .lineHeight(1.15)
            .margin(bottom = 1.cssRem)
            .fontWeight(700)
            .display(DisplayStyle.Block)

        Span(attrs = titleModifier.textAlign(TextAlign.Center).toAttrs()) {
            Text(title)
        }

        SimpleGrid(
            numColumns(1, md = 3),
            modifier = Modifier
                .id("features")
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
                        Link("https://en.wikipedia.org/wiki/Pomodoro_Technique", "Pomodoro")
                        Text(", or open-ended ")
                        Link(
                            "https://www.lesswrong.com/posts/RWu8eZqbwgB9zaerh/third-time-a-better-way-to-work",
                            "count-ups"
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
    }
}
