plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.1")
}

application {
    mainClass = "org.fitlife.App"
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}
