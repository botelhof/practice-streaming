pipeline {
    agent any
    tools {
        gradle 'Gradle'
        jdk 'jdk21'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                sh 'gradle test'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t streaming-app:${BUILD_NUMBER} .'
            }
        }
    }
}
