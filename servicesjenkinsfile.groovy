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
        stage('Build and Dockerize eureka-server') {
            steps {
                dir('eureka-server') {
                    // Build eureka-server
                     echo 'Building Eureka Server...'
                    bat 'mvn clean package'
                    echo 'Starting Eureka Server...'
                   // bat 'java -jar target/eureka-server-0.0.1-SNAPSHOT.jar'
                    bat 'mvn spring-boot:run &'

                    // Build Docker image
                  //  bat 'docker build -t vikdocker6785/eureka-server .'

                    // Push Docker image to Docker Hub
                   // bat 'docker push vikdocker6785/eureka-server'
                }
            }
        }
        stage('Build apigateway') {
            steps {
                dir('apigateway') {
                    echo 'Building API Gateway...'
                    bat 'mvn clean package'
               //     bat 'java -jar target/apigateway-0.0.1-SNAPSHOT.jar'
                    bat 'mvn spring-boot:run &'
                    // bat 'docker build -t vikdocker6785/apigateway .'
                    // bat 'docker push vikdocker6785/apigateway'
                }
            }
        }
         stage('Build and Dockerize springsecurityjwt') {
            steps {
                dir('springsecurityjwt') {
                    bat 'mvn clean package'
                     bat 'java -jar target/springsecurityjwt-0.0.1-SNAPSHOT.jar'
                    // bat 'docker build -t vikdocker6785/springsecurityjwt .'
                    // bat 'docker push vikdocker6785/springsecurityjwt'
                }
            }
        }
          stage('Build and Dockerize userservice') {
            steps {
                dir('userservice') {
                    bat 'mvn clean package'
                    bat 'java -jar target/userservice-0.0.1-SNAPSHOT.jar'
                    // bat 'docker build -t vikdocker6785/userservice .'
                    // bat 'docker push vikdocker6785/userservice'
                }
            }
        }
          stage('Build and Dockerize productservice') {
            steps {
                dir('productservice') {
                    bat 'mvn clean package'
                    bat 'java -jar target/productservice-0.0.1-SNAPSHOT.jar'
                    // bat 'docker build -t vikdocker6785/productservice .'
                    // bat 'docker push vikdocker6785/productservice'
                }
            }
        }
          stage('Build and Dockerize configserver') {
            steps {
                dir('configserver') {
                    bat 'mvn clean package'
                    bat 'java -jar target/configserver-0.0.1-SNAPSHOT.jar'
                    // bat 'docker build -t vikdocker6785/configserver .'
                    // bat 'docker push vikdocker6785/configserver'
                }
            }
        }
        stage('Build and Dockerize configclient') {
            steps {
                dir('configclient') {
                    bat 'mvn clean package'
                    bat 'java -jar target/configclient-0.0.1-SNAPSHOT.jar'
                    // bat 'docker build -t vikdocker6785/configclient .'
                    // bat 'docker push vikdocker6785/configclient'
                }
            }
        }
    }
}
