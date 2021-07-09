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
    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials', passwordVariable:'PASS',
            usernameVariable: 'USER')]) {
        sh 'docker build -t kraljict/java-maven-app:1.5 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push kraljict/java-maven-app:1.5'
    }
}

def deployDockerImage() {
    echo 'deploying the application...'
}

return this
