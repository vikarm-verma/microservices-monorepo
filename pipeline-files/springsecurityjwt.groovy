pipeline {
        agent any

        tools {
        maven 'MAVEN_HOME' // Use the name of the Maven installation you added
        }
        stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vikarm-verma/microservices-monorepo.git'
            }
        }
        stage('Build') {
            steps {
                dir('springsecurityjwt') {
                    echo 'Building springsecurityjwt service...'
                    bat 'mvn clean package'
                    echo 'Starting  springsecurityjwt...'
                    bat 'java -jar target/spring-security-jwt-0.0.1-SNAPSHOT.jar'
                }
            }
        }
        }
}
