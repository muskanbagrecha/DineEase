# Food delivery App

## API
>*POST* api/user/signup
```json
{
    "name": "string",
    "email": "string",
    "password": "string",
    "phoneNo": "string"
}
```

> *POST* api/login
```json
{
    "email": "string",
    "password": "string"
}
```

> *POST* api/restaurant/register
```json
{
    "name": "string",
    "email": "string",
    "password": "string",
    "phoneNo": "string",
    "address": {
        "street": "string",
        "city": "string",
        "state": "string",
        "pincode": "string"
    },
    "cuisine": ["string"],
    "menu": [
        {
            "name": "string",
            "price": "number",
            "category": "string",
            "description": "string",
            "image": "string",
            "isVeg": "boolean",
            "price": "number",
            "kcal": "number"
        }
    ],
    "openingTime": "string",
    "closingTime": "string",
    "images": ["string"]
}
```

> *UPDATE* api/restaurant/:id
- todo

> *POST* api/restaurant/:id/dish
```json
{
    "name": "string",
    "price": "number",
    "category": "string",
    "description": "string",
    "image": "string",
    "isVeg": "boolean",
    "price": "number",
    "kcal": "number"
}
```

> *GET* api/restaurant/:id
```json
{
    "id": "string"
}
```

> *GET* api/restaurant/menu
```json
{
    "id": "string"
}
```

> *POST* api/order
```json
{
    "userId": "string",
    "restaurantId": "string",
    "items": [
        {
            "itemId": "string",
            "quantity": "number"
        }
    ],
    "amount": "number"
}
```
>*GET* api/order/:id/status
```json
{
    "id": "string"
}
```

>*POST* api/order/:id/review
```json
{
    "rating": "number",
    "review": "string"
}
```
>*POST* api/restaurant/notifyOnAvailability
```json
{
    "userId": "string",
    "restaurantId": "string",
}
```
>*POST* api/partner/signup
```json
{
    "name": "string",
    "email": "string",
    "password": "string",
    "phoneNo": "string",
    "address": {
        "street": "string",
        "city": "string",
        "state": "string",
        "pincode": "string"
    },
    "bankDetails": {
      "accountNo": "string",
      "ifsc": "string",
      "branch": "string",
      "name": "string"
    }
}
```

>*POST* api/partner/assignDelivery
```json
{
    "orderId": "string",
    "address": {
      "street": "string",
      "city": "string",
      "state": "string",
      "pincode": "string"
    },
    "payment": {
      "mode": "string",
      "amount": "number"
    }
}
```
todo: show amt only if COD

> *POST* api/partner/:id/updateStatus
```json
{
    "orderId": "string",
    "status": "string"
}
```

> *GET* api/partner/:id/orders
```json
{
    
}
```