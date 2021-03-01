# Myretail Product API

Service to provide product information

## GET Endpoint
| From         | URI                                      | example end-points                      |
| :----------- | :--------------------------------------- | :-------------------------------------- |
| `Product` | /product/{id}     | `/product/13860428`    |

## Responses

### 200: OK

##### Example:
```json
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 19.99,
        "currency_code": "USD"
    }
}

```

#### Response Body

| Field   | Description         | Required | Type   | Example               |
|:--------|:--------------------|:--------:|:-------|:----------------------|
| id      | Product identifier    |   yes    | number | 13860428                  |
| name   | Product Description          |   yes    | string | The Big Lebowski (Blu-ray)                |
| current_price    | Product current price     |   yes    | object | [refer below](#current_price)  |

##### current_Price
| Field   | Description         | Required | Type   | Example               |
|:--------|:--------------------|:--------:|:-------|:----------------------|
| value      | Product Price    |   yes    | double | 1300                  |
| currency_code   | Amount Currency          |   yes    | string | USD               |


### 404: Not Found
Status will be 404 if product ID is not found in the system i.e. not able to find the price in NoSQL DB or able to locate product description from external source

### 500: Internal Server Error
Status will be 500 if there was an internal failure in the service

## POST Endpoint

**Endpoint:**: POST `/product/`

### Request Body
``` json
{
    "id": 13860428,
    "current_price": {
        "value": 17.99,
        "currency_code": "USD"
    }
}
```
### Response Body
```json
```
While the response body would be empty for a successful request, the response status would be `204: No Content`

### Request Body Parameters
| Field   | Description         | Required | Type   | Example               |
|:--------|:--------------------|:--------:|:-------|:----------------------|
| id      | Product identifier    |   yes    | number | 13860428                  |
| current_price    | Product current price     |   yes    | object | [refer below](#current_price)  |

#### current_Price
| Field   | Description         | Required | Type   | Example               |
|:--------|:--------------------|:--------:|:-------|:----------------------|
| value      | Product Price    |   yes    | double | 1300                  |
| currency_code   | Amount Currency          |   yes    | string | USD               |

### Responses
### 204: No content
```json
```

### 500: Internal Server Error
Status will be 500 if there was an internal failure in the service
