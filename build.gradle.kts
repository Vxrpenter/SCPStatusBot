plugins {
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
}

application.mainClass = "dev.vxrp.bot.Main" //
group = "dev.vxrp"
version = "1.0"

val jdaVersion = "5.0.0-beta.24" //

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.bspfsystems:yamlconfiguration:2.0.2")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true
}

tasks.test {
    useJUnitPlatform()
}