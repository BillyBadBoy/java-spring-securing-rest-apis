pipeline {
    agent any

    triggers {pollSCM('* * * * *')}
    
    stages {
        // implicit checkout

        stage('Build') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw clean package'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
        success {
            archiveArtifacts 'target/*.jar'
        }
    }
}
