pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }
    environment {
        TO_EMAIL_ADDRESS = credentials('main_dest_email')
	DOCKER_REGISTRY = credentials('docker_username1')
	DOCKER_CREDS = credentials('docker-creds1')
    }
    stages {
        stage('init') {
            steps {
              script {
                    gv = load "script.groovy"              
              }
            }
        }   
        stage('MavenBuild') {
            steps{
                script {
                gv.mavenBuild()
                }
            }
        }

        stage('DockerBuild'){
            steps{
                script {
                gv.buildDock(env.DOCKER_CREDS, env.DOCKER_REGISTRY)
                }
            }
        }
        stage('Deployment') {
            steps{
                script {
                gv.deployContainer(env.DOCKER_REGISTRY)
                }
            }
        }
    }
    post {
      success {
        emailext body: "Build ${currentBuild.number} completed successfully", subject: "[[ Success ]] Jenkin-tests build process notification.", to: env.TO_EMAIL_ADDRESS
      }
      failure {
        emailext body: "Build ${currentBuild.number} failed, log in to see detailed information.", subject: "[[ Failure ]] Jenkin-tests build process notification.", to: env.TO_EMAIL_ADDRESS
      }
    }
}
