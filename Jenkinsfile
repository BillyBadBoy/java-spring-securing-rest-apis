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
          - name: container1
            image: eclipse-temurin:11
            command:
            - cat
            tty: true
          containers:
          - name: container2
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
        container('container1') {
          sh 'ls -ali'
        }
      }
    }
  }
}
