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
                    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials', passwordVariable:'PASS', 
                    usernameVariable: 'USER')]) {
                        sh 'docker build -t kraljict/java-maven-app:1.4 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push kraljict/java-maven-app:1.4'
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
