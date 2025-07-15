plugins {
	kotlin("jvm") version "2.0.0"
	kotlin("plugin.spring") version "2.0.0"
	kotlin("plugin.jpa") version "2.0.0"
	kotlin("plugin.allopen") version "2.0.0"
	kotlin("plugin.noarg") version "2.0.0"

	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"

	jacoco
	id("com.github.kt3k.coveralls") version "2.12.2"
}

group = "io.dustin"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

kotlin {
	jvmToolchain(21)
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

noArg {
	annotation("jakarta.persistence.Entity")
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.kafka:spring-kafka")

	// DB
	implementation("mysql:mysql-connector-java:8.0.32")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("com.fasterxml.jackson.core:jackson-databind")

	// OpenAPI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4") // 최신 버전
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")


	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// Password
	implementation("org.springframework.security:spring-security-crypto")

	// Kotlin
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core:5.11.0")
	testImplementation("com.h2database:h2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

jacoco {
	toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		html.required.set(true)
		xml.required.set(true)
		csv.required.set(true)
	}

	doLast {
		val xmlFile = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
		val rawXml = xmlFile.readText().replace(Regex("<!DOCTYPE[^>]+>"), "")

		// 표준 XML 파서 사용
		val factory = javax.xml.parsers.DocumentBuilderFactory.newInstance()
		val builder = factory.newDocumentBuilder()
		val inputStream = rawXml.byteInputStream()
		val document = builder.parse(inputStream)

		val counters = document.getElementsByTagName("counter")
		var totalMissed = 0
		var totalCovered = 0

		for (i in 0 until counters.length) {
			val counter = counters.item(i) as org.w3c.dom.Element
			totalMissed += counter.getAttribute("missed").toInt()
			totalCovered += counter.getAttribute("covered").toInt()
		}

		val total = totalMissed + totalCovered
		val percentage = if (total > 0) (100.0 * totalCovered / total).let { "%.2f".format(it) } else "0"

		println("\n🧪 총 테스트 커버리지: $percentage% (커버된 줄 수: $totalCovered, 전체: $total)")
	}

}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			isEnabled = true
			element = "BUNDLE"
			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = "0.10".toBigDecimal()
			}
		}
	}
}

tasks.check {
	dependsOn(tasks.jacocoTestCoverageVerification)
}
