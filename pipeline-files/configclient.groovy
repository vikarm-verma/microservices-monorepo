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
                dir('configclient') {
                    echo 'Building configclient service...'
                    bat 'mvn clean package'
                    echo 'Starting configclient...'
                    bat 'java -jar target/configclient-0.0.1-SNAPSHOT.jar'
                }
            }
        }
        }
}
