package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import tools.goodtime.GOOGLE_PLAY_STORE_URL
import tools.goodtime.widgets.LinkButton

val DividerColor by StyleVariable<CSSColorValue>()
fun Modifier.dividerBoxShadow() = this.boxShadow(spreadRadius = 1.px, color = DividerColor.value())

val NavHeaderBackgroundStyle = SmoothColorStyle.extendedByBase {
    Modifier
        .backgroundColor(getNavBackgroundColor(colorMode))
        .backdropFilter(saturate(180.percent), blur(5.px))
        .dividerBoxShadow()
}

val NavHeaderStyle = NavHeaderBackgroundStyle.extendedByBase {
    Modifier
        .fillMaxWidth()
        .position(Position.Sticky)
        .top(0.percent)
        .height(96.px)
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(
        path,
        text,
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant),
        modifier = Modifier
            .padding(0.5.cssRem)
            .fontSize(1.cssRem)
            .fontWeight(500)
    )
}


// The nav header needs a higher z-index to be shown above elements with `position: sticky`
fun Modifier.navHeaderZIndex() = this.zIndex(10)

val HeroButton = CssStyle {
    base {
        // Extra height helps these hero buttons feel a bit more substantial
        Modifier.width(300.px).setVariable(ButtonVars.Height, 3.5.cssRem)
    }

    Breakpoint.MD {
        Modifier.width(150.px)
    }
}

@Composable
fun NavHeader() {
    Box(
        NavHeaderStyle.toModifier().displayIfAtLeast(Breakpoint.MD).navHeaderZIndex(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.fillMaxWidth(90.percent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeLogo()
            Spacer()
            Row(
                Modifier
                    .gap(2.cssRem)
                    .displayIfAtLeast(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinkButton(GOOGLE_PLAY_STORE_URL, HeroButton.toModifier(), "Download") {
                }
                NavLink("#features", "Features")
                NavLink("#reviews", "Testimonials")
            }
        }
    }
}

private fun getNavBackgroundColor(colorMode: ColorMode): Color.Rgb {
    return when (colorMode) {
        ColorMode.DARK -> Colors.Black
        ColorMode.LIGHT -> Colors.White
    }.copyf(alpha = 0.65f)
}
