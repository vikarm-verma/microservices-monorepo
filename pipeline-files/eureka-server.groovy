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
                dir('eureka-server')
        {
                    // Clean and build the Maven project
                    echo 'Building eureka-server service...'
                    bat 'mvn clean package'
                    echo 'Deploying the eureka-server'
                    bat 'java -jar target/eureka-server-0.0.1-SNAPSHOT.jar'
        }
            }
        }
        }
}
