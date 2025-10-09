import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.*

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "tools.goodtime"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Goodtime Productivity - A minimalist study timer for Android. Stay focused with Pomodoro technique, custom timer profiles, and detailed statistics. Join thousands achieving deep concentration and getting things done.")
            head.add {
                link(rel = "stylesheet", href = "/fonts/faces.css")
                // fade-in at load bellow
                style {
                    unsafe {
                        +"""
                        body {
                            background-color: black;
                        }
                        #_kobweb-root {
                            opacity: 0;
                            transition: opacity 0.4s ease-in;
                        }
                        #_kobweb-root.loaded {
                            opacity: 1;
                        }
                        """.trimIndent()
                    }
                }
                script {
                    unsafe {
                        raw("""
                        //<![CDATA[
                        (function() {
                            var viewport = document.querySelector('meta[name="viewport"]');
                            if (viewport) {
                                viewport.setAttribute('content', 'width=device-width, initial-scale=1.0');
                            }
                        })();

                        window.addEventListener('load', function() {
                            setTimeout(function() {
                                var root = document.getElementById('_kobweb-root');
                                if (root) {
                                    root.classList.add('loaded');
                                }
                            }, 50);
                        });
                        //]]>
                        """.trimIndent())
                    }
                }
            }
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("goodtime" /*, includeServer = true*/)

    sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here if building a fullstack app
//        }

        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
