pipeline {
    agent any
    tools {
        gradle 'Gradle'
    }
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
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
                sh 'docker build -t streaming-app:${BUILD_NUMBER} .'
            }
        }
    }
}
