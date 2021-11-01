#!/bin/bash

read -p 'Stack name: ' StackName
ProjectName=micronaut-native-image
AwsRegion=eu-west-1
AwsAccount=023016403424
DockerTagName=$StackName
DockerRepositoryName=$StackName

printf "Gradle Build... \n"
gradle clean dockerBuildNative

printf "Creating ECR repository... \n"
aws ecr create-repository --repository-name $StackName

printf "Tagging the image to push to ECR Repository... \n"
docker tag $ProjectName:latest $AwsAccount.dkr.ecr.$AwsRegion.amazonaws.com/$DockerRepositoryName:$DockerTagName

printf "Retrieve a password to authenticate to a Amazon ECR registry... \n"
aws ecr get-login-password | docker login --username AWS --password-stdin $AwsAccount.dkr.ecr.$AwsRegion.amazonaws.com

printf "Pushing image to ECR... \n"
docker push $AwsAccount.dkr.ecr.$AwsRegion.amazonaws.com/$DockerRepositoryName:$DockerTagName

printf "Creating S3 bucket... \n"
aws s3 mb s3://$StackName --region $AwsRegion

printf "Building (Cloudformation) ... \n"
sam build

printf "Deploying (Cloudformation) ... \n"
sam deploy --stack-name $StackName --s3-bucket $StackName --parameter-overrides Stage=$StackName --image-repository $AwsAccount.dkr.ecr.$AwsRegion.amazonaws.com/$DockerRepositoryName --capabilities CAPABILITY_IAM