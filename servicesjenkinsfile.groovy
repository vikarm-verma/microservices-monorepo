pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/microservices-monorepo.git'
            }
        }
        stage('Build and Dockerize eureka-server') {
            steps {
                dir('eureka-server') {
                    // Build eureka-server
                    sh 'mvn clean package'

                    // Build Docker image
                    sh 'docker build -t vikdocker6785/eureka-server .'

                    // Push Docker image to Docker Hub
                    sh 'docker push vikdocker6785/eureka-server'
                }
            }
        }
        stage('Build and Dockerize apigateway') {
            steps {
                dir('apigateway') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/apigateway .'
                    sh 'docker push vikdocker6785/apigateway'
                }
            }
        }
         stage('Build and Dockerize springsecurityjwt') {
            steps {
                dir('springsecurityjwt') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/springsecurityjwt .'
                    sh 'docker push vikdocker6785/springsecurityjwt'
                }
            }
        }
          stage('Build and Dockerize userservice') {
            steps {
                dir('userservice') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/userservice .'
                    sh 'docker push vikdocker6785/userservice'
                }
            }
        }
          stage('Build and Dockerize productservice') {
            steps {
                dir('productservice') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/productservice .'
                    sh 'docker push vikdocker6785/productservice'
                }
            }
        }
          stage('Build and Dockerize configserver') {
            steps {
                dir('configserver') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/configserver .'
                    sh 'docker push vikdocker6785/configserver'
                }
            }
        }
        stage('Build and Dockerize configclient') {
            steps {
                dir('configclient') {
                    sh 'mvn clean package'
                    sh 'docker build -t vikdocker6785/configclient .'
                    sh 'docker push vikdocker6785/configclient'
                }
            }
        }
    }
}
