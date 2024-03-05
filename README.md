# challenge-ifood-anota-ai
That is a challenge from IFood <a href="https://github.com/githubanotaai/new-test-backend-nodejs">(Anota ai)</a>. It is necessary to use a various resources from AWS and them with Spring Boot.
The application utilizes the Spring Boot API together with mongoDB to manage a product catalog system in a market scenario and interact with AWS service to storage any demand.

In the diagram bellow 2 structures can be observed:

1. Application Structure:
  - The client sends and receives requests to/from the application (product or category).
  - The application (Spring Boot) orchestrates any request to MongoDB.
  - After any registration in the database, the application sends an SNS with information to AWS.
2. AWS Strucute?
  - An SNS topic receives the information and organizes it in an SQS.
  - The SQS arranges the information in order.
  - A Lambda function is continuously listening to the SQS, and when the SQS receives information, the Lambda function captures it.
  - The Lambda function processes the information and sends it to an S3 bucket.


### Diagram

<div align="center">
  <img src="https://thumbs2.imgbox.com/ba/04/YubWGrn8_t.png" />
</div>


## Installation

1. Clone the repository:

```bash
https://github.com/gil-son/challenge-ifood-anota-ai.git
```

2. Install dependencies with Maven

3. Create a configuration with your runtime environment variables with your AWS Credentials that are used in `application.properties`

```yaml
aws.accessKeyId=${AWS_ACCESS_KEY_ID}
aws.secretKey=${AWS_ACCESS_SECRET_ID}
aws.region=${AWS_REGION}
aws.sns.topic.catalog.arn=${ARN_FROM_SNS}
```


**Mongo**

1. Run in terminal:
```bash
docker compose up -d
```

2. Create a DB for mongo using mongo express: http://localhost:8081.

3. Log with admin:pass and create a database called 'product-catalog'.

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**API PRODUCT**
```markdown
POST /api/product - Create a new product
GET /api/product - Retrieve all products
PUT /api/product/{id} - Updates a product
DELETE /api/product/{id} - Delete a product
```

**BODY**
```json
{
  "title": "Produto para postar no tópico",
  "description": "",
  "ownerId": "4444444",
  "categoryId": "659d558b0304df732ddd4587",
  "price": 10000
}
```

**API CATEGORY**
```markdown
POST /api/category - Create a new category
GET /api/category - Retrieve all categories
PUT /api/category/{id} - Updates a category
DELETE /api/category/{id} - Delete a category
```

**BODY**
```json
{
  "id": "393948882828",
  "title": "Teste",
  "description": "",
  "ownerId": "4444444"
}
```

### Resources
 - <a href="https://github.com/githubanotaai/new-test-backend-nodejs">Githubanotai</a>
 - <a href="https://github.com/Fernanda-Kipper/desafio-anotaai-backend">desafio-anotaai-backend</a>

