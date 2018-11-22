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
		
		stage('Maven build') {
		    steps {
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