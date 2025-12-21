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

## 📌 Padrões de Commit (Conventional Commits)

Este projeto segue rigorosamente a especificação [Conventional Commits](https://www.conventionalcommits.org/). Isso facilita a leitura do histórico, Code Reviews, a geração de Changelogs automáticos e o rastreamento de alterações entre os módulos do sistema.

### 🏗️ Estrutura da Mensagem

Todo commit deve seguir o formato abaixo:

    <tipo>(<escopo>): <descrição curta no imperativo>

    [corpo opcional: explicação detalhada do "porquê" e "como"]

    [rodapé opcional: referência à issue, ex: Closes #123]

### 📋 Tipos de Commits

Utilize apenas os tipos listados na tabela abaixo para categorizar seus commits:

| Tipo | Significado | Quando usar | Exemplo Real (OpenLedger) |
| :--- | :--- | :--- | :--- |
| **`feat`** | **Feature** | Nova funcionalidade, regra de negócio ou endpoint. | `feat(core): implementa calculo de taxa para boleto` |
| **`fix`** | **Bug Fix** | Correção de erros em produção ou QA. | `fix(infra): corrige NullPointer na conexão JDBC` |
| **`docs`** | **Documentation** | Alterações apenas em documentação (README, Javadoc). | `docs: atualiza instruções de setup do banco` |
| **`style`** | **Style** | Formatação, espaços, ponto e vírgula (não altera lógica). | `style(web): corrige indentação no TransacaoServlet` |
| **`refactor`** | **Refactoring** | Melhora de código que não corrige bug nem cria feature. | `refactor(core): renomeia variáveis da classe Conta` |
| **`perf`** | **Performance** | Alteração focada em melhorar desempenho. | `perf(infra): otimiza query SQL de histórico` |
| **`test`** | **Tests** | Criação ou correção de testes unitários/integração. | `test(core): adiciona testes para ContaPoupanca` |
| **`chore`** | **Chore** | Manutenção de build, ferramentas e configs (sem código prod). | `chore(maven): atualiza versão do junit no pom.xml` |
| **`build`** | **Build** | Alterações no sistema de build ou dependências externas. | `build: adiciona dependência log4j` |
| **`ci`** | **CI/CD** | Configuração de CI (GitHub Actions, Jenkins). | `ci: configura pipeline de build maven` |
| **`revert`** | **Revert** | Reversão de um commit anterior. | `revert: feat(core): remove estratégia de taxa` |

### 🎯 Escopos Permitidos (Scopes)

O escopo deve indicar o módulo ou camada onde a alteração ocorreu:

* **`core`**: Regras de negócio, Entidades, Interfaces.
* **`infra`**: Banco de dados (JDBC), Logs, Leitura de Arquivos.
* **`web`**: Servlets, Controllers, Camada HTTP.
* **`utils`**: Biblioteca auxiliar (Validadores, Formatadores).
* **`parent`**: Configurações globais do Maven (`pom.xml` raiz).

**Exemplo:** `feat(web): cria servlet de cadastro de usuários`

### ⚠️ Regras Importantes

1.  **Imperativo:** Use verbos no imperativo ("adiciona", "corrige", "remove") em vez de passado ("adicionei", "corrigido").
    * ✅ *Certo:* `feat(core): adiciona validação de saldo`
    * ❌ *Errado:* `feat(core): adicionei validação`
2.  **Formatação:** O assunto (primeira linha) não deve começar com letra maiúscula nem terminar com ponto final.
3.  **Breaking Changes:** Se a alteração quebra a compatibilidade (ex: mudança de versão do Java, remoção de método público ou alteração de API), adicione `BREAKING CHANGE:` no rodapé ou um `!` após o tipo.
    * *Exemplo:* `build(pom)!: atualiza projeto para Java 21`

## 🚀 Como Rodar (Build)

Requisitos: Java 17 + Maven 3.8+.

```bash
# Na raiz do projeto
mvn clean install