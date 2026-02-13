plugins {
    id("java")
    application
}

group = "com.MiguelmerinoTema4Gradle"
version = "1.0-SNAPSHOT"
application {
    mainClass.set("com.MiguelmerinoTema4Gradle.Main")
}
repositories {
    mavenCentral()

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")
    //Esta implementacion hace que dejen de salirme los mensajes de error"SLF4J
    implementation("org.slf4j:slf4j-simple:2.0.9")
}
//Esta parte hace que no haya errores de legibilidad por caracteres especiales y que no salgan triangulitos ni cosas raras

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec> {
    jvmArgs("-Dfile.encoding=UTF-8")
}
tasks.test {
    useJUnitPlatform()
}