plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.7.0")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")

    implementation ("com.google.code.gson:gson:2.8.9")

//    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.glassfish.expressly:expressly:5.0.0")

    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.+")
}

tasks.test {
    useTestNG()
}