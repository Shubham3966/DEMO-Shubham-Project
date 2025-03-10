pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    environment {
        AWS_REGION = 'ap-south-1' // Change to your AWS region
        APPLICATION_NAME = 'MCM_APP' // Elastic Beanstalk App Name
        ENVIRONMENT_NAME = 'MCMAPP-env' // Elastic Beanstalk Environment Name
        S3_BUCKET = 'elasticbeanstalk-ap-south-1-490004655906' // S3 bucket for Elastic Beanstalk deployment
        AWS_CREDENTIALS_ID = 'AWS_CREDENTIALS_ID' // Jenkins AWS Credentials ID
    }

    stages {

        stage('Build with Maven and Test') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Deploy to AWS Elastic Beanstalk') {
            steps {
                withAWS(credentials: "${AWS_CREDENTIALS_ID}", region: "${AWS_REGION}") {
                    script {
                        def artifact = findFiles(glob: '**/*.jar')
                        def versionLabel = "build-${env.BUILD_ID}"

                        if (artifact.length > 0 && fileExists(artifact[0].path)) { 
                            echo "File exists: ${artifact[0].path}"   
                            bat """
                                aws s3 cp ${artifact[0].path} s3://${S3_BUCKET}/${versionLabel}.jar
                                aws elasticbeanstalk create-application-version --application-name ${APPLICATION_NAME} --version-label ${versionLabel} --source-bundle S3Bucket=${S3_BUCKET},S3Key=${versionLabel}.jar
                                aws elasticbeanstalk update-environment --environment-name ${ENVIRONMENT_NAME} --version-label ${versionLabel}
                            """
                        } else {
                            error("JAR file not found")
                        }
                    }
                }
            }
        }
    }
}
