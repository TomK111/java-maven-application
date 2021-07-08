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
            when {
                expression {
                    BRANCH_NAME != "main"
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("build jar") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.buildJar()
                }
            }
        }

        stage("build docker image") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.buildDockerImage()
                }
            }
        }

        stage ("login to Nexus") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.loginToNexus()
                }
            }
        }

        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.deployDockerImage()
                }   
            }
        }
    }
}
