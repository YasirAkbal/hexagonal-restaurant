## hexagonal-restaurant

A modular monolith implementing a restaurant order management system. Built as a deliberate
exercise in applying **Hexagonal (Ports & Adapters) Architecture** and **Domain-Driven Design**
to a real-world problem, guided by Tom Hombergs' *Get Your Hands Dirty on Clean Architecture*
and Robert C. Martin's *Clean Architecture*.

## Architecture

The system is structured as three Bounded Contexts in separate Maven modules — **Order**,
**Kitchen**, and **Table** — assembled by a **configuration** module that owns all Spring
wiring and cross-cutting concerns.

Each BC follows the hexagonal pattern strictly: domain and application layers have zero
framework dependencies; Spring only appears in adapter and configuration classes.

### Bounded Contexts

**Order** — places orders, tracks lifecycle (PENDING → PREPARING → READY → DELIVERED /
CANCELLED), publishes `order.placed` and `order.cancelled` events.

**Kitchen** — receives order events, manages preparation state machine
(RECEIVED → PREPARING → READY), publishes `kitchen.order.ready`.

**Table** — tracks occupancy (AVAILABLE / OCCUPIED), publishes `table.order.delivered`.

### Inter-BC Communication

Synchronous queries between BCs (e.g. Order checking table availability) use an
**Anti-Corruption Layer** in the `configuration` module: `TableInternalAdapter` and
`MenuInternalAdapter` translate between each BC's own model and the consumer's port contract.
Neither BC holds a compile-time dependency on the other's domain — only on `shared` identifiers
and integration event types.

Async state changes flow through Kafka integration events defined in the `shared` module.

## Deliberate Design Decisions

**Rich domain model.** `Order`, `KitchenOrder`, and `Table` enforce all business rules
internally. JPA entities are separate classes; mapping goes through snapshots at the
persistence boundary so the domain never leaks persistence concerns.

**Aggregate root encapsulation.** `OrderItem` and `KitchenOrderItem` are package-private.
External code can only read them via snapshots (`getItemSnapshots()`), not mutate them
directly — a conscious DDD constraint.

**Explicit DI over component scan.** Service classes carry no Spring annotations.
`OrderConfiguration`, `KitchenConfiguration`, and `TableConfiguration` wire every bean
explicitly with `@Bean` factory methods. The domain is framework-agnostic; only adapters
and configuration know about Spring.

**Port granularity follows ISP.** Each outbound port has one method and one owner.
`PublishOrderPlacedPort` and `PublishOrderCancelledPort` are separate interfaces; the Kafka
adapter implements both. A service only depends on what it actually uses.

**Shortcut: persistence adapter as use case.** `OrderPersistenceAdapter`,
`KitchenOrderPersistenceAdapter`, and `TablePersistenceAdapter` implement their BC's
`GetXxxUseCase` directly instead of delegating to a dedicated service.
*Trade-off:* reduces boilerplate for trivial reads, but blurs the port/adapter boundary.
Documented inline with comments so the intent is explicit.

**Mapping strategy.** Full two-way mapping (domain ↔ JPA entity) for persistence adapters.
For web adapters, a lightweight one-way mapping is used where the response shape closely
matches the aggregate — dedicated request/response models were omitted where the overhead
would outweigh the benefit.

**Event publish responsibility.** Domain entities produce no events. The decision to
publish belongs to the use case: `PlaceOrderService` publishes after a successful save,
`CancelOrderService` after cancel. If the save fails, no event is published.

## Tech Stack

Java 21, Spring Boot 3.3, Spring Data JPA, Spring Kafka, MapStruct, Lombok, PostgreSQL,
Apache Kafka (KRaft), JUnit 5, Mockito, AssertJ

## Running Locally

**Prerequisites:** Docker, Java 21, Maven

```bash
# Start Kafka
docker compose up -d

# Start a PostgreSQL instance (example with Docker)
docker run -d --name pg \
  -e POSTGRES_DB=hexagonal-restaurant \
  -e POSTGRES_PASSWORD=123456 \
  -p 5432:5432 postgres:16

# Build and run
mvn -pl configuration spring-boot:run
```

Seed data (tables and menu items) is loaded automatically on startup via `DataSeeder`.
UUIDs are logged at startup — use them for the Postman requests below.

## Testing

```bash
mvn test
```

Three test layers, each scoped to its architectural boundary:

- **Domain unit tests** (`OrderPlaceOrderTest`) — pure Java, no mocks, no Spring.
- **Use case unit tests** (`PlaceOrderServiceTest`) — mocked ports, verifies orchestration
  and that publish is skipped when save fails.
- **Web controller unit tests** (`PlaceOrderControllerIntegrationTest`) — mocked the usacase and the mapper, verifies HTTTP status code
  and use case invocation.
- **Persistence slice tests** (`OrderPersistenceAdapterTest`) — `@DataJpaTest` with H2,
  verifies mapping round-trip.
- **System test** (`PlaceOrderSystemTest`) — full Spring context against real PostgreSQL,
  Kafka listeners disabled.

## API

A Postman collection with a complete happy-path flow (place order → kitchen prepares →
mark ready → deliver → close table) is in `postman/`.
