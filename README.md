# Talvix

**A Microservices-Based Professional Networking Platform**

Talvix is a professional networking platform designed to connect people, share professional updates, build meaningful connections, and discover relevant content.

Built using a **Java Spring Boot microservices architecture**, Talvix focuses on scalability, modularity, and asynchronous communication between services.

The project is being developed with modern backend technologies and distributed system concepts.

## Architecture

Talvix follows a microservices architecture where each service is responsible for a specific business domain.

```text
                    Client
                      |
                      v
                 API Gateway
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
   User Service  Post Service  Feed Service
        |             |             |
        +-------------+-------------+
                      |
              Other Services
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
 Notification     Search       Infrastructure
   Service        Service
```

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Primary programming language |
| Spring Boot | Backend application development |
| Spring Cloud | Microservices infrastructure |
| MySQL | Relational database |
| Redis | Caching and fast data access |
| Apache Kafka | Asynchronous messaging |
| Elasticsearch | Search and indexing |
| Docker | Containerization |
| Maven | Build and dependency management |

## Microservices

| Service | Responsibility | Status |
|---|---|---|
| API Gateway | Entry point and request routing | Planned |
| User Service | User registration, authentication, and profiles | In Progress |
| Post Service | Creating and managing posts | Planned |
| Feed Service | Generating personalized feeds | Planned |
| Connection Service | Managing professional connections | Planned |
| Notification Service | Handling user notifications | Planned |
| Search Service | Searching users and posts | Planned |

## Infrastructure

The project uses Docker Compose to manage supporting infrastructure services.

Currently configured infrastructure:

- **MySQL** – Persistent relational data storage.
- **Redis** – In-memory caching.
- **Apache Kafka** – Event-driven communication.
- **Elasticsearch** – Full-text search capabilities.

## Project Structure

```text
talvix/
│
├── api-gateway/
├── user-service/
├── post-service/
├── feed-service/
├── notification-service/
├── search-service/
│
├── docker-compose.yaml
├── .gitignore
└── README.md
```

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 21+
- Maven
- Docker Desktop
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Lakxhay95/talvix.git
cd talvix
```

### 2. Start infrastructure services

```bash
docker compose up -d
```

To check running containers:

```bash
docker compose ps
```

### 3. Build the services

Build each Spring Boot service using Maven:

```bash
cd user-service
mvn clean install
```

### 4. Run the User Service

```bash
mvn spring-boot:run
```

*Note: Configure the required database and application properties before starting the service.*

## Development Roadmap

- [x] Initialize microservices project structure
- [x] Configure Docker infrastructure
- [ ] Implement User Service
- [ ] Implement API Gateway
- [ ] Implement Post Service
- [ ] Implement Connection Service
- [ ] Implement Feed Service
- [ ] Implement Notification Service
- [ ] Implement Search Service
- [ ] Integrate Kafka-based event communication
- [ ] Implement Redis caching
- [ ] Integrate Elasticsearch
- [ ] Add authentication and authorization
- [ ] Add centralized logging and monitoring

## Engineering Goals

The primary engineering objectives behind Talvix are:

- Build independently deployable microservices.
- Implement scalable REST APIs.
- Explore event-driven architecture using Apache Kafka.
- Improve application performance through Redis caching.
- Implement efficient search using Elasticsearch.
- Containerize services using Docker.
- Understand distributed systems and inter-service communication.

## Repository

GitHub: https://github.com/Lakxhay95/talvix

---

**Talvix — Connect. Share. Grow.**

*An ongoing backend engineering project focused on building scalable, distributed applications.*