# ============================================================================
# Real Estate CRM/ERP backend — multi-stage Docker build for Render
# Build:  eclipse-temurin JDK 25 + the project's own Maven wrapper
# Run:    slim JRE 25 image
# ============================================================================

# ---- build stage -----------------------------------------------------------
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY . .
# mvnw may have Windows line endings; normalise before executing on Linux
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw -B -DskipTests package

# ---- run stage -------------------------------------------------------------
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/BuilderAppDemo-*.jar app.jar
EXPOSE 8080
# Render injects PORT; application.yml picks it up. Cap heap for the 512MB free tier.
CMD ["sh", "-c", "java -XX:MaxRAMPercentage=75 -jar app.jar"]
