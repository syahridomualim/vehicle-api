# Vehicle API Spec

## HTTP POST /api/v1/vehicle

- consume: json

```json
{
  "tagNumber": "string",
  "name": "string",
  "color": "RED,GREEN,YELLOW,WHITE"
}
```

- accept: json

```json
{
  "timestamp": "long",
  "httpStatus": "httpStatus",
  "code": "int",
  "message": "string",
  "data": []
}
```

## HTTP GET /api/v1/vehicle/{tagNumber}

- consume : path
    - tagNumber : string
- accept : json

```json
{
  "timestamp": "long",
  "httpStatus": "httpStatus",
  "code": "int",
  "message": "string",
  "data": {
    "tagNumber": "string",
    "name": "string",
    "color": "RED,GREEN,YELLOW,WHITE"
  }
}
```

