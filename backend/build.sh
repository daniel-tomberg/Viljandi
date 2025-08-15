#!/bin/bash
set -e

echo "Installing Java and Maven..."
apt-get update
apt-get install -y openjdk-17-jdk maven

echo "Setting JAVA_HOME..."
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

echo "Building application..."
./mvnw clean package -DskipTests

echo "Build completed successfully!"
