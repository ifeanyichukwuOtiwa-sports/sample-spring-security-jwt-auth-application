## Get Started

---

- Signup
```http request
POST http://localhost:8080/api/v1/users/sign-up
Content-Type: application/json

{
  "username": "test.user",
  "password": "123456",
  "name": "test user",
  "email": "test.user@test.com"
}
```

- Login

```http request
POST http://localhost:8080/api/v1/auth/login
Content-Type: application/json

{
  "password": "123456",
  "username": "test.user"
}
```
get token from response and use token to access other endpoints this way

- Access
```http request
GET http://localhost:8080/api/v1/products
Authorization: Bearer <generated token>

```


