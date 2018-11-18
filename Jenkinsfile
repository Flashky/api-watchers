node {
	agent any
	
	tools {
		maven 'M3_Jenkins' 
	}
	
	stage('Clone Repository') {
		// Get some code from a GitHub repository
		git 'https://github.com/Flashky/api-watchers.git'

	}

    stage('Build')
    {
                sh 'mvn --version'
	}
 
}