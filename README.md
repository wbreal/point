# point project

## 서버설정

### - 미구현

## 단위 테스트 : generated-requests.http

## 통합 테스트 : PointRepositoryTest.java

### GET http://localhost:8080/member/1/point

### GET http://localhost:8080/member/1/points?page=0&size=10&sort=seq,desc

### POST http://localhost:8080/member/1/points/earn

#### Content-Type: application/json

{
"point": "1000"
}

### POST http://localhost:8080/member/1/points/use

#### Content-Type: application/json

{
"point": "1500"
}

### DELETE http://localhost:8080/member/1/point/3

#### - 취소 후 복구 기능 구현하지 못함