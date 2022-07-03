# java-backend-springboot

Simple REST API (Spring boot, Java, JPA and Hibernate)

### create user

```
curl --location --request POST 'localhost:9000/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "rodri",
    "last_name": "quispe"
}'
```

### list users

```
curl --location --request GET 'localhost:9000/api/v1/users'
```

### get by user id

```
curl --location --request GET 'localhost:9000/api/v1/users/2'
```