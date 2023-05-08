FROM eclipse-temurin:17-jdk-alpine

ADD target/WinterSocialTalks-*.jar WinterSocialTalks.jar

ENTRYPOINT ["java", "-jar", "/WinterSocialTalks.jar"]