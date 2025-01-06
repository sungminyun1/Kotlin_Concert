# API 명세

---
## 유저 토큰 발급

#### HTTP Method
`GET`

#### Endpoint
`api/v1/queues/token/{userId}`

#### Path Parameters
| Parameter | Type   | Required | Description     |
|-----------|--------|----------|-----------------|
| `userId`  | String | Yes      | 토큰을 요청하는 유저의 ID |

#### Responses
##### 1. 대기완료
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "userId": "사용자 아이디",
          "status": "DONE"
        }
    }
    ```

##### 2. 대기중
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "userId": "사용자 아이디",
          "status": "WAITING",
          "remainingNumber": 1000 
        }
    }
    ```

##### 3. 조회 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```
  
---

## 콘서트 목록 조회

#### HTTP Method
`GET`

#### Endpoint
`api/v1/concert/list`

#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": [
          {
            "id": "concert id",
            "name": "concert name",
            "startDate": "2025-01-01T00:00:00",
            "endDate": "2025-01-01T00:00:00"
          },
          {
            "id": "concert id",
            "name": "concert name",
            "startDate": "2025-01-01T00:00:00",
            "endDate": "2025-01-01T00:00:00"
          }
        ] 
    }
    ```
  
---

## 콘서트 상세 조회

#### HTTP Method
`GET`

#### Endpoint
`api/v1/concert/{concertId}`

#### Path Parameters
| Parameter   | Type   | Required | Description        |
|-------------|--------|----------|--------------------|
| `concertId` | String | Yes      | 상세 조회하고자하는 콘서트 아이디 |

#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": [
          {
            "id": "concert detail id",
            "name": "concert name",
            "date": "2025-01-01T00:00:00",
            "seats": [
              {
                "id": 1,
                "avail": true
              },
              {
                "id": 2,
                "avail": false
              }
            ]   
          }
        ] 
    }
    ```

##### 2. 조회 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```
  
---

## 좌석 예약 요청

#### HTTP Method
`POST`

#### Endpoint
`api/v1/concert/reservation`

#### Request Body
| Parameter         | Type   | Required | Description   |
|-------------------|--------|----------|---------------|
| `userId`          | String | Yes      | 예약하려는 유저 아이디  |
| `concertDetailId` | String | Yes      | 예약하려는 콘서트 아이디 |
| `seat`             | Int    | Yes      | 예약 하려는 좌석 번호  |


#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "concertDetailId": "예약한 콘서트 아이디",
          "concertName": "예약한 콘서트 이름",
          "userId": "예약한 유저 아이디",
          "reserveId": "생성된 예약 아이디",
          "reserveTime": "2025-01-01T00:00:00"
        }   
    }
    ```

##### 2. 예약 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```
  
---

## 결제 

#### HTTP Method
`POST`

#### Endpoint
`api/v1/payment`

#### Request Body
| Parameter         | Type   | Required | Description  |
|-------------------|--------|----------|--------------|
| `reservationId`          | String | Yes      | 결제하려는 예약 아이디 |
| `userId` | String | Yes      | 결제하는 유저 아이디  |

#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "reservationId": "결제한 예약 아이디",
          "userId": "결제한 유저 아이디",
          "reserveTime": "2025-01-01T00:00:00"
        }   
    }
    ```

##### 2. 결제 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```
  
---

## 잔액 충전

#### HTTP Method
`POST`

#### Endpoint
`api/v1/member/{userId}/point`

#### Path Parameters
| Parameter | Type   | Required | Description |
|-----------|--------|----------|-------------|
| `userId`  | String | Yes      | 충전하는 유저 아이디 |

#### Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `amount`  | Long | Yes      | 충전 금액       |


#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "userId": "결제한 유저 아이디",
          "amount": 10000,
          "updatedAt": "2025-01-01T00:00:00"
        }   
    }
    ```

##### 2. 충전 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```

---

## 잔액 조회

#### HTTP Method
`GET`

#### Endpoint
`api/v1/member/{userId}/point`

#### Path Parameters
| Parameter | Type   | Required | Description |
|-----------|--------|----------|-------------|
| `userId`  | String | Yes      | 충전하는 유저 아이디 |


#### Responses
##### 1. SUCCESS
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "resCode": "SUCCESS",
        "data": {
          "userId": "결제한 유저 아이디",
          "amount": 10000,
          "updatedAt": "2025-01-01T00:00:00"
        }   
    }
    ```

##### 2. 조회 오류
- **Status Code**: `400 Bad Request`
- **Response Body**:
    ```json
    {
        "resCode": "ERROR",
        "errMsg": "에러 메시지"
    }
    ```