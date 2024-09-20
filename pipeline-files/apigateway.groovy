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
                dir('apigateway') {
                    echo 'Building API Gateway service...'
                    bat 'mvn clean package'
                    echo 'Starting api gateway...'
                    bat 'java -jar target/apigateway-0.0.1-SNAPSHOT.jar'
                }
            }
        }
        }
}
