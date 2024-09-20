pipeline {
        agent any

        tools {
        maven 'MAVEN_HOME' // Use the name of the Maven installation you added
        //jdk 'JAVA_HOME'
        }
        stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vikarm-verma/microservices-monorepo.git'
            }
        }
        stage('Build') {
            steps {
                dir('configserver') {
                    echo 'Building configserver service...'
                    bat 'mvn clean package'
                    echo 'Starting config server...'
                    bat 'java -jar target/configserver-0.0.1-SNAPSHOT.jar'
                }
            }
        }
        }
}
