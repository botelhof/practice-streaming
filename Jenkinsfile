pipeline {
    agent any
    tools {
        gradle 'Gradle'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean build'
            }
        }
        stage('Test') {
            steps {
                sh 'gradle test'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp:${BUILD_NUMBER} .'
            }
        }
    }
}
