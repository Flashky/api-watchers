pipeline {
	agent any
	stages {
		
		stage('Clone Repository (1/3)') {
			steps {
				// Get some code from a GitHub repository
				sh 'rm -r brv-commons-model'
				sh 'mkdir brv-commons-model'
				git 'https://github.com/Flashky/brv-commons-model.git brv-commons-model'
			}
		}
		
		stage('Clone Repository (2/3)') {
			steps {
				// Get some code from a GitHub repository
				sh 'rm -r repo-server-scanner'
				sh 'mkdir repo-server-scanner'
				git 'https://github.com/Flashky/repo-server-scanner.git repo-server-scanner'
			}
		}
		
		stage('Clone Repository (3/3)') {
			steps {
				// Get some code from a GitHub repository
				sh 'rm -r api-watchers'
				sh 'mkdir api-watchers'
				git 'https://github.com/Flashky/api-watchers.git api-watchers'
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