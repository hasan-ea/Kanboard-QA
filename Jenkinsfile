pipeline {
    agent any

    options {
        timestamps()
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome'], description: 'Browser for test execution')
        choice(name: 'HEADLESS', choices: ['true', 'false'], description: 'Run tests in headless mode')
        booleanParam(name: 'RUN_OBSERVATION_SUITE', defaultValue: false, description: 'Run known-issue observation suite')
    }

    environment {
        COMPOSE_FILE = 'docker-compose.yml'
        BASE_URL = 'http://localhost:8080'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare Tooling') {
            steps {
                sh 'chmod +x mvnw || true'
                sh 'chmod +x scripts/*.sh || true'
            }
        }

        stage('Start DB') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE up -d db'
            }
        }

        stage('Wait DB') {
            steps {
                sh '''
                    for i in $(seq 1 30); do
                      docker compose -f "$COMPOSE_FILE" exec -T db sh -lc 'mariadb-admin ping -h127.0.0.1 -uroot -p"$MARIADB_ROOT_PASSWORD" --silent' && exit 0
                      sleep 2
                    done
                    echo "DB did not become ready in time."
                    exit 1
                '''
            }
        }

        stage('Restore Baseline') {
            steps {
                sh './scripts/restore-baseline.sh'
            }
        }

        stage('Start App') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE up -d app'
            }
        }

        stage('Wait App') {
            steps {
                sh '''
                    for i in $(seq 1 30); do
                      curl -fsS "$BASE_URL" >/dev/null && exit 0
                      sleep 2
                    done
                    echo "App did not become ready in time."
                    exit 1
                '''
            }
        }

        stage('Verify Environment') {
            steps {
                sh './scripts/verify-baseline.sh'
            }
        }

        stage('Run Regression Tests') {
            steps {
                sh """
                    ./mvnw test \
                      -Dbrowser=${params.BROWSER} \
                      -Dheadless=${params.HEADLESS} \
                      -Dbase.url=${BASE_URL}
                """
            }
        }

        stage('Run Observation Suite') {
            when {
                expression { params.RUN_OBSERVATION_SUITE }
            }
            steps {
                sh """
                    ./mvnw test \
                      -Dsurefire.suiteXmlFiles=src/test/resources/suites/modules/project-management/create-project/testng-create-project-observation.xml \
                      -Dbrowser=${params.BROWSER} \
                      -Dheadless=${params.HEADLESS} \
                      -Dbase.url=${BASE_URL}
                """
            }
        }
    }

    post {
        always {
            sh 'mkdir -p artifacts'
            sh 'docker compose -f $COMPOSE_FILE logs --no-color > artifacts/docker-compose.log || true'
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/surefire-reports/**, artifacts/**', allowEmptyArchive: true
            sh 'docker compose -f $COMPOSE_FILE down -v || true'
        }
    }
}