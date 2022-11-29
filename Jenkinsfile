pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: foo
        spec:
          containers:
          - name: jdk11-container
            image: eclipse-temurin:11
            command:
            - cat
            tty: true
        '''
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('jdk11-container') {
          sh 'ls -ali'
        }
      }
    }
  }
}
