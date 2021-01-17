import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import java.time.Year

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot") version "2.3.7.RELEASE"

    // Add git info to the Info actuator
    id("com.gorylenko.gradle-git-properties") version "2.2.2"
}

apply(plugin = "java")

springBoot {
    buildInfo {
        properties {
            artifact = rootProject.name
            name = "Kotlin Approval Tests"
            additional = mapOf(
                    "copyright" to "Copyright @${Year.now().value} Integreety Ltd. All rights reserved"
            )
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = mutableSetOf(SKIPPED, FAILED)
    }
}

apply {
    from("dependencies.gradle.kts")
}
