pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // Checks Git every 1 min
    }

    tools {
        maven 'Maven-3.9.9'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Shubham3966/DEMO-Shubham-Project'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
