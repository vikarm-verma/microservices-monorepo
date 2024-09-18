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
                dir('eureka-server')
                {
                // Clean and build the Maven project
                bat 'mvn clean package'
                }
            }
        }

   
        stage('Deploy') {
            steps {
                dir('eureka-server')
                {
                // This step would vary based on how you're deploying the application
                // For example, if you are deploying it on a server you could SCP the JAR file, or use a service like AWS, Heroku, etc.
                echo 'Deploying the eureka-server'
                
                // Here you could start the application, example for running JAR file
                bat 'start java -jar target/eureka-server-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace after pipeline execution
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
