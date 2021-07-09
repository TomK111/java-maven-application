@Library("jenkins-shared-library")

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
        stage("test") {
            steps {
                script {
                    testApp()
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }

        stage("build docker image") {
            steps {
                script {
                    buildDockerImage()
                }
            }
        }

        stage("login to Nexus") {
            steps {
                script {
                    loginToNexus()
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    deployDockerImage()
                }   
            }
        }
    }
}
