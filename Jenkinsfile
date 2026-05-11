pipeline {
    agent any
    
    environment {
        DOCKER_HUB_USER = 'yassineabde'
        APP_NAME = 'mon-application-backend'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Image') {
            steps {
                // Construction de l'image
                sh "docker build -t ${DOCKER_HUB_USER}/${APP_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_HUB_USER}/${APP_NAME}:${BUILD_NUMBER} ${DOCKER_HUB_USER}/${APP_NAME}:latest"
            }
        }


    
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-creds',
                    passwordVariable: 'DOCKER_HUB_PASSWORD',
                    usernameVariable: 'DOCKER_HUB_USERNAME'
                )]) {
                    sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"
                    sh "docker push $DOCKER_HUB_USERNAME/${APP_NAME}:${BUILD_NUMBER}"  // ← utiliser DOCKER_HUB_USERNAME
                    sh "docker push $DOCKER_HUB_USERNAME/${APP_NAME}:latest"
                }
            }
        }
    }
    
    post {
        always {
            // Nettoyage pour ne pas encombrer le disque de la VM Azure
            sh "docker logout"
        }
    }
}
