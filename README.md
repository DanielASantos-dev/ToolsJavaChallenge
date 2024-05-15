# ToolsJavaChallenge

A API de Teste Tools foi concebida seguindo os princípios da arquitetura limpa. Dividida em módulos estruturais, compreendendo Core, UseCase, Application e Infrastructure, essa abordagem garante uma organização eficiente e manutenção simplificada.

No módulo Infrastructure, adotei o framework Spring Boot. Essa escolha estratégica não só facilita a implementação, mas também otimiza a escalabilidade e confiabilidade do projeto, garantindo uma base sólida para o desenvolvimento contínuo.

## Índice

- [Recursos](#recursos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Diagramas](#diagramas)
- [Testando a API](#testando-a-api)
- [Documentação da API com Swagger](#documentação-da-api-com-swagger)
- [Instalação e Execução](#instalação-e-execução)

## Recursos

Este projeto inclui:

- Arquitetura Limpa dividida em módulos específicos.
- Utilização do Spring Boot no módulo Infrastructure.
- Coleção JSON para testes localizada na pasta `Documentation` na raiz do projeto.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database
- Swagger para documentação da API

## Diagramas

### Diagrama de módulos e pacotes
![Diagrama de módulos e pacotes](https://github.com/DanielASantos-dev/ToolsJavaChallenge/assets/104385283/7fe6cb36-99b0-4f38-843a-5620d1daa171)

### Diagrama de entidade
![Diagrama de entidade](https://github.com/DanielASantos-dev/ToolsJavaChallenge/assets/104385283/6a903148-ebde-492d-bc18-ab43df21ebd3)

## Testando a API

Foram realizados testes unitários nas camadas: 
- Application
- Infrastructure
- Core

## Documentação da API com Swagger

Após iniciar a aplicação, você pode acessar a documentação da API através do Swagger nos seguintes endereços:

- Documentação da API: `http://localhost:8080/api-docs`
- Interface do Swagger UI: `http://localhost:8080/swagger-ui.html`

## Instalação e Execução

Instruções para instalar as dependências e executar a aplicação localmente estão abaixo:

```bash
# Clone o repositório
git clone https://github.com/DanielASantos-dev/ToolsJavaChallenge.git

# Navegue até o diretório do projeto
cd ToolsJavaChallenge

# Execute a aplicação
./gradlew bootRun

# Import a collection no diretório para o Postman
