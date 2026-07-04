# ============================================================================
# Real Estate CRM/ERP backend — production image for Render
# Lives at the repo root so Render's "Root Directory" can stay empty
# (the nested project path contains spaces, which some dashboards mishandle).
# ============================================================================

# ---- build stage -----------------------------------------------------------
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY ["Real Estate CRM ERP/BuilderAppDemo", "/app"]
# mvnw ships with Windows line endings; normalise before running on Linux
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw -B -DskipTests package

# ---- run stage -------------------------------------------------------------
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/BuilderAppDemo-*.jar app.jar
EXPOSE 8080
# Render injects PORT; application.yml reads it. Heap capped for the 512MB free tier.
CMD ["sh", "-c", "java -XX:MaxRAMPercentage=75 -jar app.jar"]
