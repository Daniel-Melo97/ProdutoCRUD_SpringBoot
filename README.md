# 🛍️ CRUD de Produtos - Spring Boot

Este é um projeto simples de CRUD (Create, Read, Update, Delete) de produtos desenvolvido com **Spring Boot**. Ele demonstra a criação de uma API RESTful para gerenciar um catálogo de produtos.

---

## 📌 Funcionalidades

- ✅ Cadastrar produtos
- 🔍 Listar todos os produtos
- 📄 Buscar produto por ID
- ✏️ Atualizar produto
- 🗑️ Deletar produto
- 👤➕ Cadastrar usuários
- 🔑 Autenticar usuários(login)

---

## 🛠️ Tecnologias Utilizadas

- [Java 17+](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://spring.io/guides/gs/rest-service/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Mysql](https://www.mysql.com/)
- [Maven](https://maven.apache.org/)
- [SpringDoc](https://springdoc.org/)

---

## 📦 Estrutura do Projeto
```bash
├── main
│   ├── java
│   │   └── com.exemple.desafioNeurotech
│   │       ├── configuration
│   │       ├── controller
│   │           ├── dto
│   │           ├── swaggerAnnotations
│   │           └── validations
│   │       ├── exceptions
│   │       ├── model
│   │       ├── repository
│   │       ├── security
│   │       └── service
│   └── resources
│       └── application.properties
```

## 🧾 Estrutura do Produto

```json
{
  "id": 1,
  "nome": "Notebook",
}
```

## 📚 Documentação da API (Swagger)

A documentação interativa da API pode ser acessada através do Swagger UI. Nela, você pode visualizar os endpoints disponíveis, seus métodos, e até testar as requisições diretamente pelo navegador.

🔗 Acesse aqui:
http://localhost:8081/swagger-ui/index.html

>**⚠️ Observação**:\É necessário que você tenha baixado e executado a aplicação em sua máquina. Somente após a aplicação estar em execução será possível acessar o Swagger.

## 🐍 Exemplo de requisição POST em Python
```python
    import requests
    import json


    urlLogin = "localhost:8081/auth/login"

    payloadLogin = json.dumps({
    "username": "usuario",
    "password": "senha"
    })
    headersLogin = {
    'Content-Type': 'application/json'
    }

    loginResponse = requests.request("POST", urlLogin, headers=headersLogin, data=payloadLogin)
    jsonData = loginResponse.json()

    token = jsonData["token"]

    url = "http://localhost:8081/cadastro"

    payload = json.dumps({
        
        "nome": "Carrinho de brinquedo",
        "descricao": "Carrinho de brinquedo para crianças",
        "preco": 3.50,
        "quantidadeEstoque": 20,
        "dataCriacao": ""
    
    })

    headers = {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
    }

    response = requests.post(url, headers=headers, data=payload)

    print("Status Code:", response.status_code)
    print("Resposta:", response.json())
```
