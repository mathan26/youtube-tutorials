# 11. Java App CI/CD Pipeline

This project is a hands-on AWS DevOps exercise to build a simple CI/CD pipeline for a Java application using GitHub as the source repository.

## Services Used

- GitHub
- AWS CodeBuild
- AWS CodePipeline
- Amazon S3

## Target Flow

```text
Git Push
  ->
GitHub
  ->
CodePipeline
  ->
CodeBuild
  ->
Deploy
```

## Goal

When code is pushed to GitHub:

1. GitHub stores the source code.
2. CodePipeline detects the change.
3. CodeBuild compiles and tests the Java application.
4. The deploy stage publishes the built artifact to the target environment.

## Important Note

`CodeBuild` and `CodePipeline` cover the build automation part.

For the final `Deploy` step, you usually choose a real deployment target such as:

- Amazon S3
- AWS Elastic Beanstalk
- AWS EC2 with CodeDeploy
- AWS ECS

For a beginner-friendly hands-on project, deploying the artifact to `Amazon S3` is the simplest option.

## Prerequisites

Before starting, make sure you have:

- An AWS account
- A GitHub account
- IAM permissions for CodeBuild, CodePipeline, S3, and CloudWatch
- AWS CLI installed and configured
- Git installed
- Java and Maven installed locally

## Project Structure

You can keep the Java app in a structure like this:

```text
Java_CI_CD_pipeline/
|-- src/
|-- pom.xml
|-- buildspec.yml
`-- README.md
```

## Step-by-Step Setup

### Step 1: Create a Java Application

Create a simple Java Maven project.

Example:

```powershell
mvn archetype:generate -DgroupId=com.example -DartifactId=java-cicd-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

This generates a starter Java application with `pom.xml`.

### Step 2: Create a GitHub Repository

1. Open GitHub.
2. Create a new repository.
3. Name it something like `java-cicd-app`.
4. Keep it public or private based on your preference.

Initialize Git locally and connect it to GitHub:

```powershell
git init
git remote add origin https://github.com/<your-username>/java-cicd-app.git
```

### Step 3: Add a `buildspec.yml` File

Create a `buildspec.yml` in the project root so CodeBuild knows how to build the app.

Example:

```yaml
version: 0.2

phases:
  install:
    runtime-versions:
      java: corretto17
  build:
    commands:
      - echo Build started on `date`
      - mvn clean package
artifacts:
  files:
    - target/*.jar
```

This file tells CodeBuild to:

- use Java 17
- run Maven build
- store the generated JAR as an artifact

### Step 4: Push Code to GitHub

```powershell
git add .
git commit -m "Initial Java CI/CD pipeline project"
git branch -M main
git push -u origin main
```

### Step 5: Create an S3 Bucket for Artifacts

CodePipeline stores artifacts between stages, and you can also use S3 for deployment output.

1. Open `Amazon S3`.
2. Create a bucket.
3. Use a unique name such as `java-cicd-pipeline-artifacts-<your-name>`.

### Step 6: Create a CodeBuild Project

1. Open `CodeBuild`.
2. Click `Create build project`.
3. Enter a project name, for example `java-cicd-build`.
4. For source provider, choose `CodePipeline`.
5. Choose a managed image.
6. Choose the runtime with Java support.
7. Enable `Use a buildspec file`.
8. Create the project.

Note:

- When CodeBuild is used inside CodePipeline, the source normally comes from CodePipeline rather than directly from GitHub.

### Step 7: Create a Connection to GitHub

CodePipeline needs access to your GitHub repository.

1. Open `CodePipeline`.
2. Start creating a new pipeline.
3. In the source stage, choose `GitHub (Version 2)`.
4. Click `Connect to GitHub`.
5. Authorize AWS to access your GitHub account.
6. Select your repository and branch.

AWS creates a connection that lets CodePipeline read your GitHub source changes.

### Step 8: Create a CodePipeline

1. Open `CodePipeline`.
2. Click `Create pipeline`.
3. Enter a pipeline name such as `java-app-pipeline`.
4. Choose or create a service role.
5. Select the S3 bucket for pipeline artifacts.

Configure stages:

### Source Stage

- Source provider: `GitHub (Version 2)`
- Connection: your GitHub connection
- Repository: your GitHub repo
- Branch: `main`

### Build Stage

- Build provider: `AWS CodeBuild`
- Project: `java-cicd-build`

### Deploy Stage

For this beginner project, choose `Amazon S3`.

- Deploy provider: `Amazon S3`
- Bucket: choose your target bucket
- Input artifact: build output from CodeBuild

If you only want CI for now, you can skip the deploy stage.

### Step 9: Test the Pipeline

Make a small code change and push again:

```powershell
git add .
git commit -m "Test pipeline trigger"
git push origin main
```

Then:

1. Open `CodePipeline`
2. Watch the pipeline execution
3. Confirm source and build stages pass successfully
4. Check the S3 bucket if you configured deployment

## Expected Outcome

After every push:

- GitHub receives the latest source code
- CodePipeline starts automatically
- CodeBuild compiles the Java app
- The generated artifact is stored and optionally deployed

## Recommended Next Improvement

To make this more realistic later, replace the S3 deploy stage with:

- Elastic Beanstalk for a simple Java hosting option
- EC2 + CodeDeploy for a classic deployment flow
- ECS for container-based deployment

## Useful AWS Services Role in This Project

- `GitHub`: source repository
- `CodeBuild`: compile, test, package
- `CodePipeline`: orchestration between stages
- `S3`: artifact storage and optional deployment target

## Summary

This project helps you learn how AWS automates Java application delivery:

1. Developer pushes code to GitHub
2. CodePipeline detects the change
3. CodeBuild builds the project
4. Deployment happens in the final stage if configured

---

If you want, the next step can be:

1. create the sample Java Maven app in this folder
2. add `buildspec.yml`
3. prepare the exact GitHub and AWS setup commands
