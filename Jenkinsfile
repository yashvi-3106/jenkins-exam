// filepath: Jenkinsfile
pipeline {
    agent any
    
    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'maven'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                bat 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running test cases...'
                bat 'mvn test'
            }
        }
        
        stage('Generate Artifact') {
            steps {
                echo 'Archiving build artifacts...'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                echo 'Build artifact generated successfully!'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed.'
        }
        success {
            echo 'Build succeeded! Artifact archived.'
        }
        failure {
            echo 'Build failed! Please check the logs.'
        }
    }
}