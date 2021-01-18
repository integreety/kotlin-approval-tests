import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import java.time.Year

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot") version "2.4.2"

    // Add git info to the Info actuator
    id("com.gorylenko.gradle-git-properties") version "2.2.2"

    // Coverage plugin
    id("jacoco")
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

tasks.jacocoTestReport {
    reports {
        csv.isEnabled = true
        csv.destination = File("${buildDir}/reports/jacoco/csv/jacoco.csv")
    }
}

tasks["test"].finalizedBy(tasks["jacocoTestReport"])

apply {
    from("dependencies.gradle.kts")
}
