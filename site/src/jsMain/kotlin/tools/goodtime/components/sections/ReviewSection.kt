package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import tools.goodtime.gradientText
import tools.goodtime.widgets.FeatureCard

@Composable
fun ReviewsSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val title = "What are users saying"
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
            numColumns(1, md = 4),
            modifier = Modifier
                .id("features")
                .fillMaxWidth()
                .padding(2.cssRem)
                .gap(2.cssRem),
        ) {
            FeatureCard(
                title = "",
                description = listOf("I am now master of time")
            )
            FeatureCard(
                title = "Timer Profiles",
                description = listOf("Strict countdown timers for techniques like Pomodoro for structured work sessions, or open-ended count-ups for deep, uninterrupted flow.")
            )

            FeatureCard(
                title = "Colored Labels",
                description = listOf(
                    "Organize tasks with colored labels, each with custom timer settings.",
                    "Archive when done."
                )
            )

            FeatureCard(
                title = "Track Progress",
                description = listOf(
                    "Detailed stats show your productivity patterns.",
                    "Organize by labels, add notes, and manually log sessions you completed offline."
                )
            )
        }
    }
}
