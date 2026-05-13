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
                    sh "docker push $DOCKER_HUB_USERNAME/${APP_NAME}:${BUILD_NUMBER}"
                    sh "docker push $DOCKER_HUB_USERNAME/${APP_NAME}:latest"
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                sh "ansible-playbook -i /var/jenkins_home/inventory.yaml /var/jenkins_home/deploy.yaml"
            }
        }
    }
    
    post {
        always {
            sh "docker logout"
        }
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
