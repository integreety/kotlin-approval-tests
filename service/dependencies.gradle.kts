val implementation by configurations
val developmentOnly by configurations
val testRuntimeOnly by configurations
val testImplementation by configurations

dependencies {
    implementation(project(":api"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")
    implementation("org.apache.commons:commons-lang3:3.10")

    //////////////////////////////////
    // Test dependencies
    testImplementation("io.mockk:mockk:1.9.3") {
        because("we want to mock Kotlin objects")
    }
    testImplementation("com.oneeyedmen:okeydoke:1.3.1") {
        because("we want to support approval tests")
    }

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0") {
        because("we want to use JUnit 5")
    }
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0") {
        because("our tests rely on jupiter-engine to resolve tests")
    }
    testRuntimeOnly("org.junit.platform:junit-platform-commons:1.7.0") {
        because("otherwise the unit tests fail")
    }
}
