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

    // lombok
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //
}

tasks.test {
    useJUnitPlatform()
}