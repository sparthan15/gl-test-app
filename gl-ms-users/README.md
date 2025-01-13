# gl-ms-users
this microservice is intended to support  adding new users and login a given user.

# Requirements
- java 11
- Springboot 2.5.14

## Endpoints
I have changed the requested endpoints to preserve REST conventions.
- Add user curl
````
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=46E320B8BD571B1CF26BAD987B86149F' \
--data-raw '{
    "email": "carlosgamboa15@gmail.com",
    "password": "1A8asasddaa",
    "name": "Carlos gamboa",
    "phones":[
        { 
            "number": 12324,
            "cityCode": 12,
            "countryCode": "12324"
        },
         { 
            "number": 1234,
            "cityCode": 1,
            "countryCode": "1234"
        }
    ]
}'
````
- Login curl
```curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3NnYW1ib2ExNUBnbWFpbC5jb20iLCJuYW1lIjoiQ2FybG9zIGdhbWJvYSIsImV4cCI6MTczNjc3MTY1OH0.13fczqvJiliueCtQL3Bw8VsK_3n04TgkOsu7MOvVhbY' \
--header 'Cookie: JSESSIONID=46E320B8BD571B1CF26BAD987B86149F' \
--data-raw '{
    "email": "carlosgamboa15@gmail.com",
    "password": "1A8asasddaa"
}''
```
