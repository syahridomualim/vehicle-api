# Vehicle API Spec

## HTTP POST /api/v1/vehicles

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
  "timestamp": "32453435",
  "httpStatus": "CREATED",
  "code": "201",
  "message": "data has been saved",
  "data": []
}
```

## HTTP GET /api/v1/vehicles/{tag_number}

- tag_number : string
- accept : json

```json
{
  "timestamp": "long",
  "httpStatus": "OK",
  "code": "200",
  "message": "invoked vehicle by tag number $tagNumber",
  "data": {
    "tagNumber": "string",
    "name": "string",
    "color": "RED,GREEN,YELLOW,WHITE"
  }
}
```

 ## HTTP GET /api/v1/vehicles
- consume
  - none
- accept: json
```json
{
  "timestamp": "long",
  "httpStatus": "OK",
  "code": "200",
  "message": "invoked vehicle by tag number $tagNumber",
  "data": [
    {
      "tagNumber": "string",
      "name": "string",
      "color": "RED,GREEN,YELLOW,WHITE"
    },
    {
      "tagNumber": "string",
      "name": "string",
      "color": "RED,GREEN,YELLOW,WHITE"
    },
    {
      "tagNumber": "string",
      "name": "string",
      "color": "RED,GREEN,YELLOW,WHITE"
    }
  ]
}
```

## HTTP PUT api/v1/vehicles/{tag_number}
- tag_number: string 
- consume: json
```json
{
  "name": "string",
  "color": "RED,GREEN,YELLOW,WHITE"
}
```
- accept: json
```json
{
  "timestamp": "long",
  "httpStatus": "OK",
  "code": "200",
  "message": "successfully edit data",
  "data": {
    "tagNumber": "string",
    "name": "string",
    "color": "RED,GREEN,YELLOW,WHITE"
  }
}
```