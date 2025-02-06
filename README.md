# Microserviço de E-mail

## Descrição
Este é um microserviço desenvolvido em Java para o envio de e-mails. Ele permite o envio de mensagens transacionais e notificacionais de forma assíncrona, garantindo maior escalabilidade e performance.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Mail**
- **MySQL**
- **SMTP do Gmail**
- **Docker Compose (para banco de dados)**
- **CI/CD (execução de testes a cada push ou PR)**

## Funcionalidades
- Envio de e-mails com suporte a HTML.
- Filas assíncronas para envio de mensagens (opcional).
- Registro de logs de e-mails enviados.
- Configuração via `application.properties`.
- Testes unitários automatizados.

## Instalação

### Requisitos
- Java 17+
- Maven 3+
- Docker e Docker Compose
- Servidor SMTP configurado (ex: Gmail)

### Configuração
As configurações do serviço estão definidas no arquivo `src/main/resources/application.properties`. Exemplo:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=usuario@gmail.com
spring.mail.password=senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Executando o Projeto
1. Clone este repositório:
   ```sh
   git clone https://github.com/ze-fernando/EmailMicroservice
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd EmailMicroservice
   ```
3. Inicie o banco de dados com Docker Compose:
   ```sh
   docker-compose up -d
   ```
4. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

## Uso

### Exemplo de Requisição HTTP
Envie uma requisição `POST` para `http://localhost:8080/send-email` com o seguinte payload:
```json
{
  "emailFrom": "destinatario@gmail.com",
  "emailTo": "myEmail@gmail.com",
  "subject": "Assunto do e-mail",
  "body": "Email Message"
}
```

## Testes
Para rodar os testes unitários:
```sh
mvn test
```

Os testes são executados automaticamente a cada push ou pull request via CI/CD.

## Contribuição
Contribuições são bem-vindas! Para contribuir:
1. Faça um fork do projeto.
2. Crie uma branch (`git checkout -b minha-feature`).
3. Commit suas mudanças (`git commit -m 'Adicionando minha feature'`).
4. Envie para o repositório (`git push origin minha-feature`).
5. Abra um Pull Request.

## Licença
Este projeto está licenciado sob a MIT License.

