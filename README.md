# Microservices Architecture: Orchestration vs. Choreography

A comparative study and implementation exploring distributed transaction management, communication patterns, and performance monitoring in a microservices environment. Developed as part of a B.E. thesis in Computer Engineering & IT at Yangon Technological University.

## 🏗️ Overview

In microservices architecture, how services communicate is critical to system scalability and maintainability. This project implements and benchmarks two primary Saga patterns:

1. **Orchestration Pattern:** A centralized coordinator (orchestrator) manages transaction steps and commands participating services.
2. **Choreography Pattern:** Decentralized, event-driven communication where services listen to events and react autonomously.

## 🛠️ Tech Stack & Tools

- **Backend:** Java, Spring Boot
- **Messaging / Communication:** RabbitMQ
- **Containerization:** Docker and Docker Compose
- **Monitoring & Telemetry:** Add the monitoring and tracing tools used in your implementation, such as Prometheus, Grafana, or Jaeger.

## ⚙️ Architecture & Flow

### Orchestration Flow

```text
Client Request
      |
      v
Order Orchestrator
      |
      v
   Service A
      |
      v
   Service B
      |
      v
   Response
```

### Choreography Flow

```text
Client Request
      |
      v
   Service A
      |
      v
 Publish Event
      |
      v
 Message Broker
      |
      v
   Service B
      |
      v
 Consume Event
```

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK-25)
- Docker
- Docker Compose
- Git

### Installation and Execution

1. **Clone the repository**

   ```bash
   git clone https://github.com/shinjir0u/microservices-approaches-performace-analysis.git
   cd microservices-approaches-performace-analysis/
   ```

2. **Add environment variables**

    Add .env files required by docker-compose.yml file in both infrasturcture and each pattern's directory


3. **Start the infrasturcture using Docker Compose**

   ```bash
   cd infrasturcture/
   docker compose up    
   ```

4. **Start the Desired Pattern using Docker Compose**

   ```bash
   cd choreography/ or cd orchestration/ 
   docker compose up --build
   ```

5. **Access the services**

   Access the application and its associated services using the ports configured in your Docker Compose file.

   If monitoring dashboards are configured, access them using their respective local URLs.

## 📊 Key Findings & Trade-offs

| Aspect | Orchestration | Choreography |
|---|---|---|
| **Communication** | A central orchestrator coordinates the workflow. | Services communicate through events. |
| **Coupling** | Workflow logic is centralized in the orchestrator. | Services are more loosely coupled through events. |
| **Debugging and Tracing** | The central coordinator makes the workflow easier to follow. | Requires tracing across multiple services and events. |
| **Complexity** | Centralized workflow logic is easier to visualize but may become overly complex. | Distributed workflow logic can be harder to understand and maintain. |
| **Maintainability** | Changes to the workflow may require changes to the orchestrator. | Changes may be localized to individual services, depending on event contracts and dependencies. |

## 📊 Key Findings & Trade-offs

Performance evaluation revealed distinct trade-offs between orchestration and choreography in terms of resource utilization, database connection efficiency, and latency.

### Overall Verdict

**Orchestration favors predictable client-facing response times and centralized workflow management, while Choreography favors faster server-side execution and more efficient database connection utilization.**

Orchestration's synchronous coordination can increase resource pressure and connection holding times under demanding workloads. In contrast, Choreography's asynchronous event-driven communication reduces blocking and distributes processing across domain services, although its performance benefits depend on workload characteristics and may come with additional client-perceived latency.

Neither pattern is universally superior. The appropriate choice depends on whether a system prioritizes immediate client responses and centralized control or asynchronous processing, service autonomy, and efficient resource utilization.

## 💡 Future Improvements

- Implement fault-tolerance mechanisms, such as retries and circuit breakers.
- Further analyze the performance and operational trade-offs between orchestration and choreography.

