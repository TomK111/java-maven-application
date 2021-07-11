def buildJar() {
    echo "building the application..."
    sh 'mvn package'
}

def testApp() {
    echo "testing the application..."
    sh "mvn test"
}

def buildDockerImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId:'nexus-credentials', passwordVariable:'PASS',
            usernameVariable: 'USER')]) {
        sh 'docker build -t 143.198.166.147:8081/java-maven-app:1.5 .'
    }
}


def loginToNexus() {
    echo "logging into Nexus OSS..."
    withCredentials([usernamePassword(credentialsId:'nexus-credentials', passwordVariable:'PASS',
            usernameVariable: 'USER')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin 143.198.166.147:8081"
    }
}
def deployDockerImage() {
    withCredentials([usernamePassword(credentialsId:'nexus-credentials', passwordVariable:'PASS',
            usernameVariable: 'USER')]) {
        sh 'docker push 143.198.166.147:8081/java-maven-app:1.5'
    }
}

return this


