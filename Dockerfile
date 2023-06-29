FROM maven:3.9.1-amazoncorretto-17

# Set the working directory inside the container
WORKDIR /Despegar_Website_Test/

# Copy the pom.xml file to the container
COPY pom.xml /Despegar_Website_Test/

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY . /Despegar_Website_Test

# Build the Maven project
RUN mvn clean install;

#RUN mvn exec:java -Dexec.mainClass="starter.menu.StarterMenu"
CMD mvn exec:java -Dexec.mainClass="cucumber.test.TestRunner"