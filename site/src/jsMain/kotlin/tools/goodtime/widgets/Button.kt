package tools.goodtime.widgets
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.colors.shifted
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

enum class ButtonShape {
    RECTANGLE,
    ROUNDED_RECTANGLE
}

private fun getButtonModifier(shape: ButtonShape): Modifier {
    return Modifier
        .padding(0.px)
        .thenIf(shape == ButtonShape.ROUNDED_RECTANGLE, Modifier.borderRadius(32.px))
}

val PrimaryButtonVariant = ButtonStyle.addVariant {
    val backgroundColor = Colors.White
    base {
        Modifier
            .backgroundColor(backgroundColor)
            .color(Color.rgb(0x292929))
    }

    hover {
        Modifier.backgroundColor(backgroundColor.darkened())
    }
}

val NormalButtonVariant = ButtonStyle.addVariant {
    val colorMode = colorMode
    base {
        Modifier
            .backgroundColor(colorMode.toPalette().background)
            .border(width = 1.px, color = Colors.DimGray, style = LineStyle.Solid)
            .color(colorMode.toPalette().color)
    }
    hover {
        Modifier.backgroundColor(colorMode.toPalette().background.shifted(colorMode))
    }
}

/**
 * Create a [Button] which is styled with primary or secondary colors.
 *
 * @param primary If true, use styles that call this button out as one associated with a major action you want to draw
 *   attention to.
 * @param content If set, renders custom content on the button. If both this and [text] is specified, then this
 *   content will be rendered to the left of the text with a bit of margin. This is particularly useful for rendering
 *   logos.
 */
@Composable
fun ThemedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    shape: ButtonShape = ButtonShape.ROUNDED_RECTANGLE,
    primary: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier.then(getButtonModifier(shape)),
        if (primary) PrimaryButtonVariant else NormalButtonVariant
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            content()
            if (!text.isNullOrEmpty()) {
                SpanText(text = text, modifier = Modifier.fontWeight(800))
            }
        }
    }
}

/**
 * Create a [ThemedButton] which acts likes a link, navigating to some target URL when clicked on.
 */
@Composable
fun LinkButton(
    path: String,
    modifier: Modifier = Modifier,
    text: String? = null,
    shape: ButtonShape = ButtonShape.ROUNDED_RECTANGLE,
    primary: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    val ctx = rememberPageContext()
    ThemedButton(onClick = { ctx.router.navigateTo(path) }, modifier, text, shape, primary, content)
}