# Use plain Ubuntu base image
FROM ubuntu:22.04

# Prevent interactive prompts
ENV DEBIAN_FRONTEND=noninteractive

# Install required packages: Java, Maven, Git
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy application source code into container
COPY . .

# Build Spring Boot application
RUN mvn clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run Spring Boot application
CMD ["java", "-jar", "target/GreetingApp-0.0.1-SNAPSHOT.jar"]
