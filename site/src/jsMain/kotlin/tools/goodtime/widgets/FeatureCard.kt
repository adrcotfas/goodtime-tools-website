package tools.goodtime.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import tools.goodtime.gradientText

@Composable
fun FeatureCard(
    title: String,
    description: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(1.px, LineStyle.Solid, Colors.DimGray.copyf(alpha = 0.38f))
            .borderRadius(32.px)
            .padding(1.5.cssRem)
            .minHeight(100.px),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(2.cssRem)) {
            SpanText(
                title,
                modifier = Modifier
                    .fontSize(1.25.cssRem)
                    .gradientText()
                    .fontWeight(600)
                    .margin(bottom = 0.75.cssRem)
            )
        }

        Column {
            description.forEach {
                SpanText(
                    it,
                    modifier = Modifier
                        .fontSize(1.cssRem)
                        .color(Colors.Gray)
                )
            }

        }
    }
}

@Composable
fun FeatureCardRich(
    title: String,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(1.px, LineStyle.Solid, Colors.DimGray.copyf(alpha = 0.38f))
            .borderRadius(32.px)
            .padding(1.5.cssRem)
            .minHeight(100.px),
        horizontalAlignment = Alignment.Start
    ) {
        SpanText(
            title,
            modifier = Modifier
                .fontSize(1.25.cssRem)
                .gradientText()
                .fontWeight(600)
                .margin(bottom = 0.75.cssRem)
        )
        description()
    }
}
