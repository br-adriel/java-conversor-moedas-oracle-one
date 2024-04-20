# Conversor de moedas 🪙

Projeto desenvolvido durante o acompanhamento do programa Oracle One em parceria
com a Alura.

O projeto se trata de um programa de linha de comando no qual é possível
converter valores de uma moeda para outra.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## Executando localmente

1. Acesse o site da [ExchangeRate API](https://www.exchangerate-api.com/) e crie
uma conta para obter uma chave de acesso

2. Faça o download do repositório e abra a pasta do projeto na IDE de sua 
preferência.

3. Faça uma cópia do arquivo `.env.example` e renomeie a cópia como `.env`

4. Insira a sua chave de acesso no arquivo `.env`, no campo
`EXCHANGE_RATE_API_KEY`

5. Execute o comando a seguir para instalar as dependências do projeto:

    ```bash
    mvn clean install
    ```

6. Compile e execute o projeto
