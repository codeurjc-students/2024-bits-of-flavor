#################################################
# Base image for the Angular build container
#################################################
FROM node as buildFront
WORKDIR /frontend
COPY ../frontend/package*.json ../frontend/angular.json ../frontend/tsconfig*.json ./
RUN npm install
COPY ../frontend/src ./src
RUN npm run build -- --configuration production --base-href="/"

#################################################
# Base image for the maven build container
#################################################
FROM maven as buildBack
WORKDIR /app
COPY ../backend/pom.xml .

#RUN mvn clean verify 

COPY backend/src ./src
COPY --from=buildFront /frontend/dist/frontend/browser/ ./src/main/resources/static/
RUN mvn clean package -DskipTests

#################################################
# Base image for the application container
#################################################
FROM openjdk:23-jdk-slim

# Copy the jar from the build stage
COPY --from=buildBack /app/target/bof-0.0.1-SNAPSHOT.jar java-app.jar

# Specify the container's entrypoint
CMD [ "java", "-jar", "java-app.jar" ]