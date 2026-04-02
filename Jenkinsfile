pipeline {
    agent { label 'windows' }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome'], description: 'Browser for test execution')
        choice(name: 'HEADLESS', choices: ['true', 'false'], description: 'Run tests in headless mode')
        booleanParam(name: 'RUN_OBSERVATION_SUITE', defaultValue: false, description: 'Run known-issue observation suite')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Regression Tests') {
            steps {
                bat ".\\mvnw.cmd test -Dbrowser=%BROWSER% -Dheadless=%HEADLESS%"
            }
        }

        stage('Run Observation Suite') {
            when {
                expression { params.RUN_OBSERVATION_SUITE }
            }
            steps {
                bat ".\\mvnw.cmd test -Dsurefire.suiteXmlFiles=src/test/resources/suites/modules/project-management/create-project/testng-create-project-observation.xml -Dbrowser=%BROWSER% -Dheadless=%HEADLESS%"
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
    }
}