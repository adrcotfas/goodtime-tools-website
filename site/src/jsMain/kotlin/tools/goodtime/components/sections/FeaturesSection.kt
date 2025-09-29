package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Div
import tools.goodtime.toSitePalette

@Composable
fun FeaturesSection() {
    SimpleGrid(
        numColumns(1, md = 2),
        modifier = Modifier
            .id("features").fillMaxWidth(),
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
