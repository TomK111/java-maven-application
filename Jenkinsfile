def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.8'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }

        stage("build docker image") {
            steps {
                script {
                    gv.buildDockerImage()
                }
            }
        }

        stage("deploy") {
            steps {
                gv.deployDockerImage()
            }
        }
    }
}

