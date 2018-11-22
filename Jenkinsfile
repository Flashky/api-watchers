pipeline {
	agent any
	stages {
		
		stage('Clone Repository') {
			steps {
				// Get some code from a GitHub repository
				git 'https://github.com/Flashky/brv-commons-model.git'
				git 'https://github.com/Flashky/repo-server-scanner.git'
				git 'https://github.com/Flashky/api-watchers.git'
			}

		}
		
		stage('Maven clean install') {
			agent {
				docker { image 'maven:3.6.0' }
			}
			
			steps {
				sh 'mvn -f brv-commons-model/pom.xml clean install'
				sh 'mvn -f server-scanner/pom.xml clean install'
				sh 'mvn -f api-watchers/pom.xml clean install'
			}
		}

		stage('Docker build') {
			steps {
				sh 'docker build -t flashk/api-watchers:0.0.1 .'
			}
		}
	}
}