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
                    echo 'Building eureka-server service...'
                    bat 'mvn clean package'
                    echo 'Deploying the eureka-server'
                    bat 'start java -jar target/eureka-server-0.0.1-SNAPSHOT.jar'
                    }
                        dir('apigateway') {
                        echo 'Building API Gateway service...'
                        bat 'mvn clean package'
                    }
                    dir('springsecurityjwt') {
                        echo 'Building springsecurityjwt service...'
                        bat 'mvn clean package'
                    }
                        dir('userservice') {
                        echo 'Building userservice service...'
                        bat 'mvn clean package'
                    }
                        dir('productservice') {
                        echo 'Building productservice service...'
                        bat 'mvn clean package'
                    }
                    dir('configserver') {
                        echo 'Building configserver service...'
                        bat 'mvn clean package'
                    }
                    dir('configclient') {

                        echo 'Building configclient service...'
                        bat 'mvn clean package'
                    }
                }
            }


            stage('Deploy') {
                steps {
                    // dir('eureka-server')
                    // {
                    // echo 'Deploying the eureka-server'
                    // bat 'start java -jar target/eureka-server-0.0.1-SNAPSHOT.jar'
                    // }
                    dir('apigateway')
                    {
                    echo 'Starting api gateway...'
                    bat 'start java -jar target/apigateway-0.0.1-SNAPSHOT.jar'
                    }
                    dir('springsecurityjwt')
                    {
                    echo 'Starting springsecurityjwt...'
                    bat 'start java -jar target/springsecurityjwt-0.0.1-SNAPSHOT.jar'
                    }
                    dir('userservice')
                    {
                    echo 'Starting userservice...'
                    bat 'start java -jar target/userservice-0.0.1-SNAPSHOT.jar'
                    }
                    dir('productservice')
                    {
                    echo 'Starting productservice...'
                    bat 'start java -jar target/productservice-0.0.1-SNAPSHOT.jar'
                    }
                    dir('configserver')
                    {
                    echo 'Starting configserver...'
                    bat 'start java -jar target/configserver-0.0.1-SNAPSHOT.jar'
                    }
                    dir('configclient')
                    {
                    echo 'Starting client...'
                    bat 'start java -jar target/configclient-0.0.1-SNAPSHOT.jar'
                    }
                }
            }
        }

        post {
            // always {
            //     // Clean up workspace after pipeline execution
            //     cleanWs()
            // }
            success {
                echo 'Pipeline completed successfully!'
            }
            failure {
                echo 'Pipeline failed.'
            }
        }
        }
