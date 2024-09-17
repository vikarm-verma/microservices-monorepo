pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME' // Use the name of the Maven installation you added
          jdk 'JAVA_HOME'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vikarm-verma/microservices-monorepo.git'
            }
        }
        stage('Build and Dockerize eureka-server') {
            steps {
                dir('eureka-server') {
                    // Build eureka-server
                    bat 'mvn clean package'
                    bat 'java -jar target/eureka-server.jar'

                    // Build Docker image
                  //  bat 'docker build -t vikdocker6785/eureka-server .'

                    // Push Docker image to Docker Hub
                   // bat 'docker push vikdocker6785/eureka-server'
                }
            }
        }
        stage('Build and Dockerize apigateway') {
            steps {
                dir('apigateway') {
                    bat 'mvn clean package'
                    bat 'java -jar target/apigateway.jar'
                    // bat 'docker build -t vikdocker6785/apigateway .'
                    // bat 'docker push vikdocker6785/apigateway'
                }
            }
        }
         stage('Build and Dockerize springsecurityjwt') {
            steps {
                dir('springsecurityjwt') {
                    bat 'mvn clean package'
                     bat 'java -jar target/springsecurityjwt.jar'
                    // bat 'docker build -t vikdocker6785/springsecurityjwt .'
                    // bat 'docker push vikdocker6785/springsecurityjwt'
                }
            }
        }
          stage('Build and Dockerize userservice') {
            steps {
                dir('userservice') {
                    bat 'mvn clean package'
                    bat 'java -jar target/userservice.jar'
                    // bat 'docker build -t vikdocker6785/userservice .'
                    // bat 'docker push vikdocker6785/userservice'
                }
            }
        }
          stage('Build and Dockerize productservice') {
            steps {
                dir('productservice') {
                    bat 'mvn clean package'
                    bat 'java -jar target/productservice.jar'
                    // bat 'docker build -t vikdocker6785/productservice .'
                    // bat 'docker push vikdocker6785/productservice'
                }
            }
        }
          stage('Build and Dockerize configserver') {
            steps {
                dir('configserver') {
                    bat 'mvn clean package'
                    bat 'java -jar target/configserver.jar'
                    // bat 'docker build -t vikdocker6785/configserver .'
                    // bat 'docker push vikdocker6785/configserver'
                }
            }
        }
        stage('Build and Dockerize configclient') {
            steps {
                dir('configclient') {
                    bat 'mvn clean package'
                    bat 'java -jar target/configclient.jar'
                    // bat 'docker build -t vikdocker6785/configclient .'
                    // bat 'docker push vikdocker6785/configclient'
                }
            }
        }
    }
}
