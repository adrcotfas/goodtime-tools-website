package tools.goodtime.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import org.jetbrains.compose.web.dom.Div
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import tools.goodtime.gradientText
import tools.goodtime.widgets.ReviewCard
import tools.goodtime.widgets.ThemedButton

@Composable
fun <T> StaggeredGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    numColumns: Int = 3,
    itemContent: @Composable (T) -> Unit
) {
    // Reorder items to achieve horizontal-first layout with column-based masonry
    val reorderedItems = buildList {
        // Distribute items round-robin across columns
        val columns = List(numColumns) { mutableListOf<T>() }
        items.forEachIndexed { index, item ->
            columns[index % numColumns].add(item)
        }
        // Flatten columns to get reordered list
        columns.forEach { column ->
            addAll(column)
        }
    }

    Div(
        attrs = modifier.styleModifier {
            property("column-count", numColumns.toString())
            property("column-gap", "2rem")
        }.toAttrs()
    ) {
        reorderedItems.forEach { item ->
            Div(
                attrs = Modifier
                    .styleModifier {
                        property("break-inside", "avoid")
                        property("display", "inline-block")
                        property("width", "100%")
                    }
                    .margin(bottom = 2.cssRem)
                    .toAttrs()
            ) {
                itemContent(item)
            }
        }
    }
}

data class ReviewCardData(val name: String, val review: String, val link: String)

@Composable
fun ReviewsSection() {
    var expandLevel by remember { mutableStateOf(0) }
    Column(modifier = Modifier.id("reviews"), horizontalAlignment = Alignment.CenterHorizontally) {
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

        val reviews = listOf(
            ReviewCardData(
                name = "David Baimov",
                review = "The app helps me to track my study hours and keeps me accountable. This app is the best productivity hack I have found",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=16a26d4a-a63f-4cea-bc43-c5ba9e2a739c&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Harley Fine",
                review = "I am now master of time",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=5a4365d7-534b-4726-9ddc-aa38bd6f7275&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Denis Beliy",
                review = "I've been searching for a suitable Pomodoro timer for a long time, and this one meets all my expectations. I'm very pleased with the interface, the ability to customize everything, and the option to purchase a premium account permanently.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=37a0a5ed-6dfb-44ba-b632-a9edef78ccf7&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Eva Schmidt",
                review = "Minimal, perfect. This app has truly improved my study habits. So simple to use, I just choose my study block time and break times. I do two short breaks and one long, and this allows me to get chores done and decompress a bit. I've been sooo much more productive with it, and I've recommended it to other students. No cutesy stuff, no junk, just a simple utility. Would be great for interval training too!",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=b5d70174-37f2-408c-983d-b6cd5f5371b6&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Miggy Antiquina",
                review = "This is my first time writing a review for an app, I just had to considering the quality of this productivity timer. It's very simple to use and the premium version is cheap AND a one-time purchase. I have been using this app for more than 6 months now and had encountered little trouble. Definitely one of the best productivity apps out there!",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=2996c7a2-b7f5-4491-9169-259216ce2c3d&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Atharava Goyal",
                review = "I am using this app from over a year now and it's a very good experience having this app. Taking its premium is worth it. It has very simple like it says minimalist productivity timer ⌛. Just open the app and tap. Overall very good app if u want to study using pomodoro technique u can try this.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=26bbb906-01ca-438e-8d5f-11fcccd5934b&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(name = "モコモコ cafe",
                review = "It's so simple, it's great! It's so simple, with only the time and number of sessions you've done so far displayed at the top. However, it's also great that it has the bare minimum of essential functions, but still offers a wide selection! I especially like the way you can visually see the amount of work you've done by time and session, and the way you can separate data by tagging. Other people's apps like this are full of unnecessary features, so they don't suit me, who pursues functional beauty, but this app is packed with all the functional beauty I could want. I'm already in love with it...no, I'm so in love with it that I'm willing to pay for it.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=2cbd0eef-977b-4337-8b80-7cd3deb0148c&corpus=PUBLIC_REVIEWS"),
            ReviewCardData(
                name = "Marki Prior",
                review = "It's great. It saves your studying time and everything. If you pay, you can get tags so you can create them for any topic or thing you're going to do. But it's also great to use it for free. It saves the whole process for you.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=0fc304af-5c1f-46d1-96dc-c4a93fda0a6f&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Pavel Ternavskii",
                review = "notifications work. No ads whatsoever. The entire app, from the simple design to the do not disturb mode feature during a work session, helps you focus on your work. It's clear the developers wrote the app for themselves. Kudos to you!",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=341b2bd9-e055-444c-9f74-c573a391c32d&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Google Play user",
                review = "This is a great time tracker! If you don't cheat, it really boosts your productivity. The free version is quite efficient—it doesn't constantly charge you money, it's not cluttered with ads, and the app's basic functionality is accessible. Overall, I'm happy—for me, it's the best app in its category \uD83D\uDC4D",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=d3d710b5-a11c-45d4-a878-ee2ea0bf7762&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Juan Monroy-Nieto", review =
                    "The latest version has been a welcome leap forward. The UI is now more intuitive — especially the hint wheel for the controls. The feature that has represented a unique improvement is the count up option where you accrue downtime as you go. It's fantastic for workflows where you would rather not be interrupted. It offers great flexibility. I've been using this app for 10 years now and it has always been stable and the dev is very responsive.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=f1de33f6-3933-461f-86b2-5519b8265b15&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Данил «snecht» Мусихин",
                review = "Let me tell you right away: if you, like me, were looking for a decent Pomodoro timer, this is it. The interface is streamlined: it's easy to set a Pomodoro and a break, and there's nothing extra on the home page. There are also tags for those who like them, and there are almost no ads. Conclusion: a very useful app.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=82fd946a-f6ce-4136-bb46-f016d7e52fda&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Zakir Hussain",
                review = "Very clean interface, no ads or data collection nonsense. Kudos guys! I bought the premium version just to support them.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=ddb4b67b-8679-4683-8a65-e492753d15a5&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Aisha Ansar",
                review = "This app is really good! I personally found it very useful for myself. It tracks your hours of work which helps in tracking your progress.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=52724bf3-2fc2-4b03-a23b-f1ea6826aaa8&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Clover Duong",
                review = "Thank you for making this app! I like it because it doesn't have all the distracting bells and whistles. Looks nice!",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=d0493192-e3a9-4e68-900a-e9262add9888&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "",
                review = "Exactly what I wanted. Lightweight, simple, and it actually works. The free version is robust enough to do the job well, and I was so pleased with the app that I was happy to upgrade to the pro version. The labels weren't something I was looking for but they have been tremendously helpful! Another bonus is that the app works with my Garmin watch so I can have my phone in another room and start or skip breaks from my watch.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=d1901cc7-95f2-4a0f-a128-957929d84608&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Jean-Luc",
                review = "I really like this app because it allows me to \"see\" where the hours of my day are spent. I currently have over 20 tags, and I launch it as soon as I do something. In an update, I would like to be able to sort the tags alphabetically when I look at my time spent statistics.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=eabfe709-46ee-4753-a511-4bf56efa6954&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "アイダヒデキ（Kevin)",
                review = "All the main features are available for free. It's a very reasonable app. The default setting is 25 minutes, which can be used for the Pomodoro technique. Once you download and open the app, you can start working right away by simply tapping the screen. If you don't want to miss a moment of motivation, don't read this review and download it right away.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=fcc5b31a-a6f0-4841-bd4e-36b9b18207cf&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Google Play user",
                review = "No distractions, no redundant features, no ads, extremely lightweight, and doesn't impact battery life. A simple timer that automatically switches between work sessions and breaks, notifications, and a session counter. If this method of concentration works for me, I will absolutely not hesitate to contribute with a purchase. Not because it's necessary for its operation, but as a contribution to the developer.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=f5af0c69-2e1e-4a6d-9df3-8243c56a596d&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Dave Nelson",
                review = "Everything you need to stay on track, and no fluff - or slimy ads in the free version, which speaks to the author's ethos. (Let's pay good people a fair price for great tools!) Just got the pro version after reviewing a dozen others, and using for a few days, and this is by far has the most professional feel and ease of use. ... It could stand to have a bit more customization of colors, a more accessible Do Not Disturb mode setting (or DND per label would be rad!), and a bit more intuitive interface in places (editing session history).. but this is really just asking for shiny sprinkles on an already epic vegan cake – cool to have, but I'm already gorging and satisfied in a really healthy way. \uD83E\uDD18\uD83D\uDE01",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=869f12c7-0036-4ea1-9a25-4285cb0bddb7&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Nilson Cristiano Marcondes Brino",
                review = "I'm very satisfied with this app. It helps me better understand and manage my time usage on activities. Tags make it easy to detail each timer, which can be viewed later in the statistics report. It helps me stay focused, and in my case, it also helps me remember not to sit at work for too long without breaks, which is beneficial for my health and for generating new ideas.",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=90d3776e-ea0f-48aa-858e-961b15c30945&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Sarah",
                review = "I've tried out several Pomodoro timers and this is the one for me. Finally, a simple, ad-free app that does exactly what it needs to do, and no more. Too many apps try to be a to-do list, planner, time tracker and life coach and end up doing none of these things well. This one is just perfect. ",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=d048668f-7ecb-4564-abb9-4505d211b34a&corpus=PUBLIC_REVIEWS"
            ),
            ReviewCardData(
                name = "Stephen Hart",
                review = "My productivity has gone up 100%. Not only is it nice just to have a minimalistic timer and the fact that it has statistics, break times, and no ads makes it quite literally the best timer I've ever had. This is one of the very few apps I would recommend if somebody ask me. This app is perfect for me, and although it might not be the best for some people, who like to be guilted into working, this app just works for me. Statistics and break times are amazing. 10 out of 10 app, works amazingly!!!!",
                link = "https://play.google.com/console/u/0/developers/8401747129284940974/app/4972226269313781186/user-feedback/review-details?reviewId=8a5b6c36-1c38-4e13-8a17-fa190159485f&corpus=PUBLIC_REVIEWS"
            ),
        )

        val displayedReviews = when (expandLevel) {
            0 -> reviews.take(5)
            1 -> reviews.take(10)
            else -> reviews
        }

        StaggeredGrid(
            items = displayedReviews,
            modifier = Modifier.displayUntil(Breakpoint.MD).fillMaxWidth().padding(2.cssRem),
            numColumns = 1,
            itemContent = { ReviewCard(it) }
        )
        StaggeredGrid(
            items = displayedReviews,
            modifier = Modifier.displayIfAtLeast(Breakpoint.MD).fillMaxWidth().padding(2.cssRem),
            numColumns = 3,
            itemContent = { ReviewCard(it) }
        )

        if (expandLevel < 2) {
            ThemedButton(
                text = "Show More",
                onClick = { expandLevel++ },
                primary = false,
                modifier = Modifier.width(300.px).setVariable(ButtonVars.Height, 2.5.cssRem)
            )
        }
    }
}
