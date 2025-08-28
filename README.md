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
  "descricao": "Notebook com tela touchscreen",
  "preco": 550.99,
  "quantidadeEstoque": 30,
  "dataCriacao": "2025-08-26T19:15:10.693Z"
}
```

## 📚 Documentação da API (Swagger)

A documentação interativa da API pode ser acessada através do Swagger UI. Nela, você pode visualizar os endpoints disponíveis, seus métodos, e até testar as requisições diretamente pelo navegador.

🔗 Acesse aqui:
http://desafio-neurotech-aws-java-env.eba-dqsvrzpf.us-east-2.elasticbeanstalk.com/

## 🚀 API Collection

Uma collection do Postman está disponível na pasta [`/postman_collection/desafio-neurotech-daniel.postman_collection.json`](./postman_collection/desafio-neurotech-daniel.postman_collection.json)

### Como importar no Postman
1. Abra o Postman
2. Clique em **Import**
3. Selecione o arquivo `.desafio-neurotech-daniel.postman_collection.json`

## 🐍 Exemplo de requisição POST em Python
```python
    import requests
    import json


    urlLogin = "http://desafio-neurotech-aws-java-env.eba-dqsvrzpf.us-east-2.elasticbeanstalk.com/auth/login"

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

    url = "http://desafio-neurotech-aws-java-env.eba-dqsvrzpf.us-east-2.elasticbeanstalk.com/cadastro"

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
