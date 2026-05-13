pipeline {
    agent any

    environment {
        DOCKER_HUB_USER  = 'yassineabde'
        APP_NAME         = 'mon-application-backend'
        IMAGE_FULL       = "${DOCKER_HUB_USER}/${APP_NAME}"
        INVENTORY        = '/var/jenkins_home/inventory.yaml'
        PLAYBOOK         = '/var/jenkins_home/deploy.yaml'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Image') {
            steps {
                sh """
                    docker build -t ${IMAGE_FULL}:${BUILD_NUMBER} .
                    docker tag  ${IMAGE_FULL}:${BUILD_NUMBER} ${IMAGE_FULL}:latest
                """
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-creds',
                    passwordVariable: 'DOCKER_HUB_PASSWORD',
                    usernameVariable: 'DOCKER_HUB_USERNAME'
                )]) {
                    sh """
                        echo \$DOCKER_HUB_PASSWORD | docker login -u \$DOCKER_HUB_USERNAME --password-stdin
                        docker push ${IMAGE_FULL}:${BUILD_NUMBER}
                        docker push ${IMAGE_FULL}:latest
                    """
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                sshagent(credentials: ['production-ssh-key']) {
                    sh """
                        ansible-playbook \
                            -i ${INVENTORY} \
                            ${PLAYBOOK} \
                            -e image_tag=${BUILD_NUMBER} \
                            -e build_number=${BUILD_NUMBER}
                    """
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
            // Clean up local images to save disk space
            sh """
                docker rmi ${IMAGE_FULL}:${BUILD_NUMBER} || true
                docker rmi ${IMAGE_FULL}:latest || true
            """
        }
        success {
            echo "✅ Build #${BUILD_NUMBER} deployed successfully!"
        }
        failure {
            echo "❌ Build #${BUILD_NUMBER} failed — check logs above."
        }
    }
}
