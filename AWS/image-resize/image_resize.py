import boto3, json, os, io
from PIL import Image

s3 = boto3.client('s3')
OUTPUT_BUCKET = os.environ.get('OUTPUT_BUCKET')
SIZES = [(150, 150), (300, 300), (800, 600)]

def lambda_handler(event, context):
    for record in event['Records']:
        bucket = record['s3']['bucket']['name']
        key    = record['s3']['object']['key']

        # Download original image
        obj = s3.get_object(Bucket=bucket, Key=key)
        img = Image.open(io.BytesIO(obj['Body'].read()))

        # Resize and upload for each size
        for w, h in SIZES:
            resized = img.copy()
            resized.thumbnail((w, h))
            buf = io.BytesIO()
            resized.save(buf, format=img.format or 'JPEG')
            buf.seek(0)
            out_key = f"resized/{w}x{h}/{key}"
            s3.put_object(
                Bucket=OUTPUT_BUCKET,
                Key=out_key,
                Body=buf,
                ContentType=obj['ContentType']
            )
        print(f"✅ Resized {key} → 3 sizes")
    return {'statusCode': 200}