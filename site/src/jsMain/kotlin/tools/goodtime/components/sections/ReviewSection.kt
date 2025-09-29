package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import tools.goodtime.toSitePalette

@Composable
fun ReviewsSection() {
    Div(
        Modifier
            .id("reviews")
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
