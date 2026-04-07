import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('tbl-url-shortner')

def lambda_handler(event, context):
    try:
        short_code = event.get('pathParameters', {}).get('shortCode')

        if not short_code:
            return {
                'statusCode': 400,
                'body': json.dumps({'error': 'shortCode is missing'})
            }

        response = table.get_item(Key={'shortCode': short_code})
        item = response.get('Item')

        if not item:
            return {
                'statusCode': 404,
                'body': json.dumps({'error': 'Short URL not found'})
            }

        return {
            'statusCode': 301,
            'headers': {
                'Location': item['originalUrl']
            },
            'body': ''
        }

    except Exception as e:
        return {
            'statusCode': 500,
            'body': json.dumps({'error': str(e)})
        }