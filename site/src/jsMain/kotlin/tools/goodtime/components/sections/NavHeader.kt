package tools.goodtime.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import tools.goodtime.components.widgets.IconButton
import tools.goodtime.toSitePalette

val DividerColor by StyleVariable<CSSColorValue>()
fun Modifier.dividerBoxShadow() = this.boxShadow(spreadRadius = 1.px, color = DividerColor.value())

val NavHeaderHeight by StyleVariable<CSSLengthNumericValue>()

@InitSilk
fun initNavHeaderHeight(ctx: InitSilkContext) = with(ctx.stylesheet) {
    registerStyle("html") {
        base { Modifier.setVariable(NavHeaderHeight, 64.px) }
        Breakpoint.MD { Modifier.setVariable(NavHeaderHeight, 122.px) }
    }
}

val NavHeaderBackgroundStyle = SmoothColorStyle.extendedByBase {
    Modifier
        .backgroundColor(getNavBackgroundColor(colorMode))
        .backdropFilter(saturate(180.percent), blur(5.px))
        .dividerBoxShadow()
}

val NavHeaderDarkenedBackgroundStyle = NavHeaderBackgroundStyle.extendedByBase {
    Modifier
        .backgroundColor(getNavBackgroundColor(colorMode).copyf(alpha = 0.8f))
}

val NavHeaderStyle = NavHeaderBackgroundStyle.extendedByBase {
    Modifier
        .fillMaxWidth()
        .position(Position.Sticky)
        .top(0.percent)
        .height(NavHeaderHeight.value())
}

@Composable
private fun NavLink(path: String, text: String, onClick: (() -> Unit)? = null) {
    val sitePalette = ColorMode.current.toSitePalette()

    Link(
        path,
        text,
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant),
        modifier = Modifier
            .padding(0.5.cssRem)
            .fontSize(1.cssRem)
            .fontWeight(600)
            .color(sitePalette.brand.primary)

            .then(if (onClick != null) Modifier.onClick { onClick() } else Modifier)
    )
}

@Composable
private fun DesktopMenuItems() {
    NavLink("#download", "Download")
    NavLink("#features", "Features")
    NavLink("#testimonials", "Testimonials")
}

@Composable
private fun MobileMenuItems(closeMenu: () -> Unit) {
    Column(Modifier.gap(1.cssRem)) {
        NavLink("#download", "Download") { closeMenu() }
        NavLink("#features", "Features") { closeMenu() }
        NavLink("#testimonials", "Testimonials") { closeMenu() }
    }
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

// Note: When the user closes the side menu, we don't immediately stop rendering it (at which point it would disappear
// abruptly). Instead, we start animating it out and only stop rendering it when the animation is complete.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

// The nav header needs a higher z-index to be shown above elements with `position: sticky`
fun Modifier.navHeaderZIndex() = this.zIndex(10)

@Composable
fun HomeLogo() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(0.25.cssRem),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(
            0.5.cssRem,
            Alignment.Start
        )
    ) {
        Image(src = "goodtime_logo.png", modifier = Modifier.size(2.5.cssRem))
        Row() {
            SpanText(
                text = "Goodtime", modifier = Modifier
                    .fontSize(1.3.cssRem)
                    .fontWeight(500)
                    .display(DisplayStyle.Block)
            )
            SpanText(
                text = " Productivity", modifier = Modifier
                    .fontSize(1.3.cssRem)
                    .fontWeight(200)
                    .display(DisplayStyle.Block)
            )
        }
    }
}

@Composable
fun NavHeader() {
    var colorMode by ColorMode.currentState
    Box(NavHeaderStyle.toModifier().navHeaderZIndex(), contentAlignment = Alignment.Center) {
        Row(
            Modifier.fillMaxWidth(90.percent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeLogo()
            Spacer()
            // Desktop navigation
            Row(
                Modifier
                    .gap(2.cssRem)
                    .displayIfAtLeast(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DesktopMenuItems()
            }

            // Mobile navigation
            Row(
                Modifier
                    .displayUntil(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

                // Toggle button that opens/closes menu and changes icon
                IconButton(onClick = {
                    menuState = when (menuState) {
                        SideMenuState.CLOSED -> SideMenuState.OPEN
                        SideMenuState.OPEN -> SideMenuState.CLOSING
                        SideMenuState.CLOSING -> SideMenuState.CLOSED
                    }
                }) {
                    if (menuState == SideMenuState.CLOSED) {
                        HamburgerIcon()
                    } else {
                        CloseIcon()
                    }
                }

                if (menuState != SideMenuState.CLOSED) {
                    SideMenu(
                        menuState,
                        close = { menuState = menuState.close() },
                        onAnimationEnd = { if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED }
                    )
                }
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

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Black.copy(alpha = 50))
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(12.cssRem, 40.percent, 16.cssRem))
                    .align(Alignment.CenterEnd)
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(2.cssRem)
                    .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 300.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 1.cssRem, bottomLeft = 1.cssRem)
                    .boxShadow(blurRadius = 0.5.cssRem, color = Colors.Black.copy(alpha = 30))
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = com.varabyte.kobweb.compose.foundation.layout.Arrangement.End
                ) {
                    CloseButton(onClick = { close() })
                }

                Column(
                    Modifier
                        .padding(left = 1.5.cssRem)
                        .gap(1.5.cssRem)
                        .fontSize(1.2.cssRem),
                    horizontalAlignment = Alignment.Start
                ) {
                    MobileMenuItems(close)
                }
            }
        }
    }
}
