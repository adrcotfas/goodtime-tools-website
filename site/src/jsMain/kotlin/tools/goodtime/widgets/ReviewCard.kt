package tools.goodtime.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.LineHeight
import com.varabyte.kobweb.compose.css.MinWidth
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.cssRules
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.theme.colors.palette.SilkWidgetColorGroups
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import tools.goodtime.components.sections.ReviewCardData

val UnsizedButtonStyle = ButtonStyle.addVariantBase {
    Modifier
        .height(Height.Inherit)
        .minWidth(MinWidth.Inherit)
        .lineHeight(LineHeight.Inherit)
        .fontSize(100.percent)
        .padding(0.px)
        .margin(0.px)
}

val UnstyledButtonVariant = UnsizedButtonStyle.extendedBy {
    base {
        Modifier
            .backgroundColor(Colors.Transparent)
            .fontWeight(FontWeight.Inherit)
    }
    cssRules(hover, active) {
        Modifier.backgroundColor(Colors.Transparent)
    }
}

@Composable
fun ReviewCard(data: ReviewCardData) {
    ReviewCard(data.name, data.review, data.link)
}

@Composable
fun ReviewCard(name: String, review: String, link: String = "") {
    Column(
        modifier = Modifier
            .border(1.px, LineStyle.Solid, Colors.DimGray.copyf(alpha = 0.38f))
            .borderRadius(32.px)
            .padding(1.5.cssRem)
            .minHeight(100.px),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(1.cssRem)
    ) {
        SpanText(
            review,
            modifier = Modifier
                .fontSize(1.cssRem)
                .color(Colors.Gray)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.5.cssRem, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val ctx = rememberPageContext()
            SpanText(
                name,
                modifier = Modifier
                    .fontSize(1.cssRem)
            )
            Button(onClick = {
                ctx.router.navigateTo(link)
            }, variant = UnstyledButtonVariant) {
                Image(src = "/link.svg", modifier = Modifier.size(16.px))
            }
        }
    }
}