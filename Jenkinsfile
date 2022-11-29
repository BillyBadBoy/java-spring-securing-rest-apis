podTemplate(yaml: '''
  apiVersion: v1
  kind: Pod
  spec:
    containers:
    - name: jdk11-container
      image: eclipse-temurin:11
      command:
      - sleep
      args: 
      - 99d
      volumeMounts:
      - name: m2
        mountPath: "/var/m2"
    volumes:
    - emptyDir:
        medium: ""
      name: "workspace-volume"
    - name: m2
      persistentVolumeClaim:
        claimName: mvn-pv-claim
    ''') {
    node(POD_LABEL) {
        container('jdk11-container') {

            stage('clone repo') {
            //      git 'https://github.com/BillyBadBoy/java-spring-securing-rest-apis.git'
                sh 'ls -ali'  
            }
        
            // stage('resolve deps') {
            //     sh './mvnw dependency:resolve -Dmaven.repo.local=/var/m2'
            // }

            // stage('clean') {
            //     sh './mvnw clean -Dmaven.repo.local=/var/m2'
            // }
        
            // stage('build') {
            //         sh './mvnw package -DskipTests=true -Dmaven.repo.local=/var/m2'
            // }

            // stage('test') {
            //         sh './mvnw test -Dmaven.repo.local=/var/m2'
            // }
        }
    }
}
