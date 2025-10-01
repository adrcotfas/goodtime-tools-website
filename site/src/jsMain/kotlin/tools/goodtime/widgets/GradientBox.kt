package tools.goodtime.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.silk.style.*
import org.jetbrains.compose.web.css.percent

sealed interface GradientBoxKind : ComponentKind

val GradientBoxStyle = CssStyle.base<GradientBoxKind> {
    Modifier
        .backgroundImage(
            radialGradient(RadialGradient.Shape.Circle, CSSPosition(25.percent)) {
                add(Color.rgba(80, 191, 172, 0.5f))
                add(Colors.Transparent, 25.percent)
            }
        )
}

val GradientBoxVariant70 = GradientBoxStyle.addVariant {
    base {
        Modifier
            .backgroundImage(
                radialGradient(RadialGradient.Shape.Circle, CSSPosition(50.percent)) {
                    add(Color.rgba(80, 191, 172, 0.5f))
                    add(Colors.Transparent, 60.percent)
                }
            )
    }
}

/**
 * Create a [Box] with a fancy, color aware gradient behind it.
 */
@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    variant: CssStyleVariant<GradientBoxKind>? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(GradientBoxStyle.toModifier(variant).then(modifier), contentAlignment, content = content)
}
