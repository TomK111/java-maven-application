pipeline {
    agent any
    tools {
        maven 'maven-3.8'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    sh 'mvn package'
                }
            }
        }

        stage("build docker image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword:(
                    credentialsId:'nexus-credentials',
                    passwordVariable:'PASS',
                    usernameVariable: 'USER')]) {
                        sh 'docker build -t 134.122.20.106:8083/java-maven-app:1.4 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin 134.122.20.106:8083"
                        sh 'docker push 134.122.20.106:8083/java-maven-app:1.4'
                    }
                }
            }
        }

        stage("deploy") {
            steps {
                echo "deploying application..."
            }
        }
    }
}
