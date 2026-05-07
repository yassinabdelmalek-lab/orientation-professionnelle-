pipeline {
    agent any

    stages {
        stage('Validation Connexion') {
            steps {
                echo '✅ SSH GitHub : OK'
            }
        }
        stage('Validation Docker') {
            steps {
                // On vérifie si Jenkins peut appeler le moteur Docker de la VM
                sh 'docker ps'
                echo '✅ Docker Socket : OK'
            }
        }
        stage('Infos Système') {
            steps {
                sh 'uname -a'
                sh 'whoami'
            }
        }
    }
}
