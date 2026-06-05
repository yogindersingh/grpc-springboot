# gRPC Spring Boot

A multi-module Spring Boot project exploring gRPC for inter-service communication, built around a simulated stock trading system.

## Architecture

Three microservices communicate via gRPC, with a REST gateway exposing HTTP endpoints to clients.

```
grpc-springboot (parent)
├── proto/              — Shared Protocol Buffer definitions
├── user-service/       — gRPC server: user profiles & stock trading
├── stock-service/      — gRPC server: real-time price streaming
└── aggregator-service/ — REST gateway: gRPC client + SSE for browsers
```

### Service Ports

| Service            | HTTP  | gRPC |
|--------------------|-------|------|
| user-service       | 8001  | 6565 |
| aggregator-service | 8002  | —    |
| stock-service      | 8003  | 6566 |

## gRPC Services

### StockService (`stock-service.proto`)
```protobuf
rpc GetPriceUpdates(google.protobuf.Empty) returns (stream PriceUpdate)
```
Streams random price updates every second for `APPLE`, `GOOGLE`, `AMAZON`, and `MICROSOFT`.

### UserService (`user-service.proto`)
```protobuf
rpc GetUserInformation(UserInformationRequest) returns (UserInformation)
rpc TradeStock(StockTradeRequest) returns (StockTradeResponse)
```
Manages user balances, portfolio holdings, and buy/sell transactions.

## REST Endpoints (aggregator-service)

| Method | Path              | Description                              |
|--------|-------------------|------------------------------------------|
| GET    | `/users/{userId}` | Get user info and portfolio              |
| POST   | `/stocktrade`     | Buy or sell a stock                      |
| GET    | `/stockUpdates`   | SSE stream of live price updates         |

## Tech Stack

- **Java 17**, **Spring Boot 3.3.3**
- **gRPC 1.58.0** + `grpc-server/client-spring-boot-starter 3.1.0`
- **Protocol Buffers 3.25.1**
- **Spring Data JPA** + **H2** (in-memory database)
- **Server-Sent Events** for browser streaming

## Getting Started

### Build

```bash
mvn clean install
```

The `proto` module is built first and generates Java stubs used by all other modules.

### Run

Start each service in a separate terminal:

```bash
# Terminal 1
cd user-service && mvn spring-boot:run

# Terminal 2
cd stock-service && mvn spring-boot:run

# Terminal 3
cd aggregator-service && mvn spring-boot:run
```

The user-service database seeds three users (Sam, Mike, John) with a balance of 10,000 each on startup.

### Test

```bash
mvn test
```

Tests use in-process gRPC channels to avoid requiring live services. The aggregator-service tests include a `StockMockService` that replaces the real stock-service.

## Key Concepts Demonstrated

- **Server-side streaming** — stock-service pushes a continuous `PriceUpdate` stream
- **Unary RPCs** — user lookup and trade execution
- **gRPC error handling** — `@GrpcAdvice` maps domain exceptions to gRPC status codes (`NOT_FOUND`, `INVALID_ARGUMENT`, `FAILED_PRECONDITION`)
- **SSE bridge** — aggregator-service consumes a gRPC stream and forwards updates to browser clients as Server-Sent Events
- **In-process testing** — integration tests wire gRPC services without a network
