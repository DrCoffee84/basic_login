# Spring  boot basic_login

## How to use 

### Login

```bash
curl --location --request POST 'localhost:8080/api/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "dboullon",
    "password": "12345"
}'
```

###  Get Provincias

```bash
curl --location --request GET 'localhost:8080/api/provincia' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NDc2MDM0ODgsImlhdCI6MTY0NzYwMTI2OCwianRpIjoiZGJvdWxsb24ifQ.xcYA8lMgF9MhPr5AYOO_p67CkCods9EvsJ6vIRDsbdyt5m2mO6kMtpiuzCwnwMk7EbdcH2PW6cnHXzgBr7_tfw'
```

### Health Service

Verify that there is a connection to the db

```bash
curl --location --request GET 'localhost:8080/api/health'
```
