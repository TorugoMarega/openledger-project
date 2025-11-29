# 🏦 OpenLedger (Projeto Openheimer)

O **OpenLedger** é um sistema de **Core Banking** responsável por gerenciar contas, clientes e processar transações financeiras com alta integridade.

Este projeto é um laboratório de engenharia de software focado na evolução arquitetural: partimos propositalmente de uma **Stack Legada ("Toxic Stack")** para entender as dores da complexidade acidental, e evoluiremos para uma arquitetura **Moderna** na Fase 2.

## 🏗 Arquitetura Modular (Maven)

O projeto segue uma estrutura Multi-Module:

* **openledger-parent**: Gerenciador de dependências e versões (BOM).
* **ledger-core**: Regras de negócio puras, entidades e interfaces (Isolado de Web/Banco).
* **ledger-utils**: Biblioteca de utilitários, validação e formatação.
* **ledger-infra**: Implementação de persistência (JDBC), leitura de arquivos e configs.
* **ledger-web**: Camada de API HTTP (Servlets -> futuro Spring Controller).

## 🚫 Regras da Fase 1: A Era Legacy

Nesta fase inicial, simulamos um ambiente corporativo antigo. As seguintes restrições técnicas são **mandatórias**:

| Categoria | Stack Permitida (Legacy) | Stack Proibida (Por enquanto) |
| :--- | :--- | :--- |
| **Framework** | Nenhum (Java Puro) | Spring Boot, Quarkus, Micronaut |
| **Persistência** | JDBC Puro + SQL Nativo | JPA, Hibernate, Spring Data |
| **Config** | XML (`db-config.xml`, `web.xml`) | YAML, Properties, Annotations |
| **Logging** | Log4j 1.2.17 | SLF4J, Logback |
| **Datas** | Joda-Time 2.10 | Java Time API (Java 8+) |
| **Testes** | JUnit 4 | JUnit 5 (Jupiter) |

## 🚀 Como Rodar (Build)

Requisitos: Java 17 + Maven 3.8+.

```bash
# Na raiz do projeto
mvn clean install