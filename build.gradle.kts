plugins {
    id("java")
    // 🌱 이미 만든 프로젝트에 spring 적용 🌱
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.fastcampus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // 🌱 이미 만든 프로젝트에 spring 적용 🌱
    // 🌱 Spring Data JPA 추가 🌱
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // API 기능 구현 후 파라미터로 들어왔을 때 에러 처리
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // mysql
    // mysql 연동을 위한 connector 라이브러리
    runtimeOnly("com.mysql:mysql-connector-j")

    // Query DSL을 사용하기 위한 Dependency 추가
    implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor ("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor ("jakarta.persistence:jakarta.persistence-api")


    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-gson:0.12.6")


    // lombok
    implementation ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")

    // test
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // REST-Assured, H2 Memory DB 추가
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    runtimeOnly("com.h2database:h2")

}

tasks.test {
    useJUnitPlatform()
}

/**
 * QueryDSL Build Options
 */
val querydslDir = "${layout.projectDirectory}/build/generated/querydsl"

sourceSets {
    getByName("main").java.srcDirs(querydslDir)
}

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory = file(querydslDir)
}

tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}
