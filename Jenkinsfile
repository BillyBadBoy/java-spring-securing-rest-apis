pipeline {
    agent any

    triggers {pollSCM('* * * * *')}
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/BillyBadBoy/java-spring-securing-rest-apis.git', branch: 'master' 
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw clean package'
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
    }
}
