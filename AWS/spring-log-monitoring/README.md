# Spring Boot Log Monitoring with AWS CloudWatch

A Spring Boot application that monitors logs and sends them to AWS CloudWatch for centralized log management and analysis.

## 📋 Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **AWS Account** with IAM user/role configured
- **Postman** (optional, for API testing)

## 🔧 AWS Setup

### 1. IAM Permissions

Attach the **`CloudWatchFullAccess`** managed policy to your IAM user/role (for testing).

For production, use this least-privilege policy:
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "logs:CreateLogGroup",
                "logs:CreateLogStream",
                "logs:PutLogEvents",
                "logs:DescribeLogGroups",
                "logs:DescribeLogStreams"
            ],
            "Resource": "arn:aws:logs:*:*:*"
        },
        {
            "Effect": "Allow",
            "Action": [
                "cloudwatch:PutMetricData",
                "cloudwatch:ListMetrics"
            ],
            "Resource": "*"
        }
    ]
}
```

### 2. Configure AWS Credentials

**Option A: Environment Variables (Recommended)**
```bash
# Windows (CMD)
set AWS_ACCESS_KEY_ID=your-access-key
set AWS_SECRET_ACCESS_KEY=your-secret-key
set AWS_REGION=us-east-1

# Windows (PowerShell)
$env:AWS_ACCESS_KEY_ID="your-access-key"
$env:AWS_SECRET_ACCESS_KEY="your-secret-key"
$env:AWS_REGION="us-east-1"

# Linux/Mac
export AWS_ACCESS_KEY_ID=your-access-key
export AWS_SECRET_ACCESS_KEY=your-secret-key
export AWS_REGION=us-east-1
```

**Option B: AWS CLI**
```bash
aws configure
# Enter your Access Key, Secret Key, and default region
```

**Option C: Credentials File** (`~/.aws/credentials`)
```ini
[default]
aws_access_key_id = your-access-key
aws_secret_access_key = your-secret-key
region = us-east-1
```

## 🚀 Running the Application

### 1. Build the Project
```bash
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

### 3. Verify Startup
Check the console output for:
```
Initializing CloudWatch Logs client for region: us-east-1
```

## 🧪 Testing the Application

### Option 1: Using Postman

1. Import the collection: **File → Import →** select `postman/Spring Log Monitoring.postman_collection.json`
2. The `{{base_url}}` variable defaults to `http://localhost:8080`
3. Run the requests in order (see test flow below)

### Option 2: Using cURL

**Log an INFO message:**
```bash
curl -X POST http://localhost:8080/api/logs/info \
  -H "Content-Type: application/json" \
  -d "{\"message\": \"Application started successfully\"}"
```

**Log a WARN message:**
```bash
curl -X POST http://localhost:8080/api/logs/warn \
  -H "Content-Type: application/json" \
  -d "{\"message\": \"High memory usage detected: 85%\"}"
```

**Log an ERROR message:**
```bash
curl -X POST http://localhost:8080/api/logs/error \
  -H "Content-Type: application/json" \
  -d "{\"message\": \"Database connection failed: timeout after 30s\"}"
```

**Log with custom level:**
```bash
curl -X POST http://localhost:8080/api/logs \
  -H "Content-Type: application/json" \
  -d "{\"level\": \"DEBUG\", \"message\": \"Debug trace information\"}"
```

**Get recent logs:**
```bash
curl http://localhost:8080/api/logs?limit=50
```

**Get recent logs filtered by level:**
```bash
curl "http://localhost:8080/api/logs?level=ERROR&limit=20"
```

**Get log counts:**
```bash
curl http://localhost:8080/api/logs/count
```

**Get error logs only:**
```bash
curl http://localhost:8080/api/logs/errors?limit=20
```

**Health check:**
```bash
curl http://localhost:8080/actuator/health
```

## 📝 Complete Test Flow

### Step 1: Start the Application
```bash
mvn spring-boot:run
```

### Step 2: Generate Logs
Run these commands to create sample logs:

```bash
# INFO logs
curl -X POST http://localhost:8080/api/logs/info -H "Content-Type: application/json" -d "{\"message\": \"User login successful\"}"
curl -X POST http://localhost:8080/api/logs/info -H "Content-Type: application/json" -d "{\"message\": \"Order processed: #12345\"}"

# WARN logs
curl -X POST http://localhost:8080/api/logs/warn -H "Content-Type: application/json" -d "{\"message\": \"API rate limit approaching: 80%\"}"

# ERROR logs
curl -X POST http://localhost:8080/api/logs/error -H "Content-Type: application/json" -d "{\"message\": \"Payment gateway timeout\"}"
curl -X POST http://localhost:8080/api/logs/error -H "Content-Type: application/json" -d "{\"message\": \"Failed to send email notification\"}"

# DEBUG logs
curl -X POST http://localhost:8080/api/logs -H "Content-Type: application/json" -d "{\"level\": \"DEBUG\", \"message\": \"Cache miss for key: user:123\"}"
```

### Step 3: Retrieve and Verify Logs
```bash
# Get all recent logs
curl http://localhost:8080/api/logs?limit=10

# Get only ERROR logs
curl http://localhost:8080/api/logs/errors

# Get log statistics
curl http://localhost:8080/api/logs/count
```

### Step 4: Verify in AWS CloudWatch Console

1. Go to **AWS Console → CloudWatch → Log groups**
2. Find the log group: **`spring-log-monitoring`**
3. Click on it to see log streams
4. Click a log stream to view the actual log events
5. You should see all the logs you sent via the API

## ⚙️ Configuration

### application.properties

| Property | Default | Description |
|---|---|---|
| `spring.application.name` | `log-monitoring` | Application name |
| `server.port` | `8080` | Server port |
| `aws.cloudwatch.region` | `us-east-1` | AWS region |
| `aws.cloudwatch.enabled` | `true` | Enable/disable CloudWatch |
| `aws.cloudwatch.log-group` | `spring-log-monitoring` | CloudWatch log group name |
| `awslogs.enabled` | `true` | Enable/disable logback appender |

### Disable CloudWatch (Local Testing Only)

Edit [`src/main/resources/application.properties`](src/main/resources/application.properties):
```properties
aws.cloudwatch.enabled=false
awslogs.enabled=false
```

## 📁 Project Structure

```
spring-log-monitoring/
├── src/main/java/com/tech/mathan/spring_log_monitoring/
│   ├── SpringLogMonitoringApplication.java    # Main application class
│   ├── config/
│   │   └── CloudWatchConfig.java              # AWS CloudWatch configuration
│   └── controller/
│       └── LogController.java                 # REST API endpoints
├── src/main/resources/
│   ├── application.properties                 # Application configuration
│   └── logback-spring.xml                     # Logback configuration for CloudWatch
├── postman/
│   └── Spring Log Monitoring.postman_collection.json
└── pom.xml                                    # Maven dependencies
```

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/logs` | Log message with custom level |
| `POST` | `/api/logs/info` | Log INFO message |
| `POST` | `/api/logs/warn` | Log WARN message |
| `POST` | `/api/logs/error` | Log ERROR message |
| `GET` | `/api/logs?limit=50&level=ERROR` | Get recent logs (filterable) |
| `GET` | `/api/logs/count` | Get log counts by level |
| `GET` | `/api/logs/errors?limit=20` | Get ERROR logs only |
| `GET` | `/actuator/health` | Health check |
| `GET` | `/actuator/metrics` | Application metrics |

## 🐛 Troubleshooting

### CloudWatch Connection Issues
- Verify AWS credentials are set correctly
- Check IAM permissions include CloudWatch access
- Ensure the region matches your AWS setup
- Check console output for error messages

### Logs Not Appearing in CloudWatch
- Wait 1-2 minutes for logs to propagate
- Verify `aws.cloudwatch.enabled=true`
- Check the log group name matches in AWS Console
- Look for errors in application console output

### Application Won't Start
- Ensure Java 17+ is installed: `java -version`
- Run `mvn clean install` to resolve dependencies
- Check port 8080 is not in use

## 📦 Building for Production

```bash
mvn clean package -DskipTests
java -jar target/log-monitoring-1.0.0-SNAPSHOT.jar
```

## 🔒 Security Notes

- **Never commit AWS credentials** to version control
- Use IAM roles instead of access keys when running on AWS infrastructure
- Rotate access keys regularly
- Use least-privilege policies in production
