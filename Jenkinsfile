pipeline {
	agent any
	tools {
		maven 'M3_Jenkins' 
	}
	stages {
		
		stage('Clone Repository') {
			steps {
				// Get some code from a GitHub repository
				git 'https://github.com/Flashky/api-watchers.git'
			}

		}

		stage('Build') {
			steps {
				sh 'mvn -f api-watchers/pom.xml clean install'
			}
		}
	}
}