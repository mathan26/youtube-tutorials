import json
import boto3
import string
import random

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('tbl-url-shortner')

def generate_short_code(length=6):
    chars = string.ascii_letters + string.digits
    return ''.join(random.choices(chars, k=length))

def lambda_handler(event, context):
    try:
        body = json.loads(event.get('body', '{}'))
        original_url = body.get('url')

        if not original_url:
            return {
                'statusCode': 400,
                'headers': {'Content-Type': 'application/json'},
                'body': json.dumps({'error': 'url is required'})
            }

        short_code = generate_short_code()

        table.put_item(Item={
            'shortCode': short_code,
            'originalUrl': original_url
        })

        return {
            'statusCode': 200,
            'headers': {'Content-Type': 'application/json'},
            'body': json.dumps({
                'shortCode': short_code,
                'shortUrl': f'Use GET /{short_code} to redirect'
            })
        }

    except Exception as e:
        return {
            'statusCode': 500,
            'headers': {'Content-Type': 'application/json'},
            'body': json.dumps({'error': str(e)})
        }