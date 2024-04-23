def mavenBuild() {
  echo "Building the Jar file..."
  sh "mvn test"
  sh "mvn clean package"
  sh "mvn surefire-report:report"
}

def buildDock(String dockerRegistry) {
  echo "Building Docker image..."
  docker_app = docker.build("$dockerRegistry/hello_world1:${env.BUILD_ID}")
  docker_app.push()
  docker_app.push('latest')
}

def deployContainer(String dockerRegistry) {
  sh "docker run --name hello_world1 -d  $dockerRegistry/hello_world1:${env.BUILD_ID}"
  sh "echo 'Container will run for 3 minutes'"
  sh "sleep 180"
  sh "docker rm -f hello_world1"
}

return this 
