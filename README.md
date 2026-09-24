# Sistema de Cadastro de Clientes em Java

Projeto desenvolvido para a disciplina **Desenvolvimento de Sistemas**, aplicando conceitos das unidades de **Lógica de Programação** e **Programação Orientada a Objetos (POO)**.

O sistema consiste em uma aplicação de console para gerenciamento de clientes, permitindo trabalhar com **Pessoa Física (PF)** e **Pessoa Jurídica (PJ)**.

## 📌 Situação-problema

Muitas pequenas empresas ainda realizam o controle de seus clientes utilizando cadernos ou planilhas, o que pode gerar problemas como:

- Dados incompletos;
- Clientes duplicados;
- E-mails inválidos;
- CPF ou CNPJ incorretos;
- Dificuldade para localizar e atualizar informações.

O objetivo deste projeto é desenvolver uma aplicação simples capaz de cadastrar, listar, buscar, atualizar e remover clientes**, realizando também a validação dos dados informados pelo usuário.

---

## ⚙️ Funcionalidades

O sistema permite:

- Cadastrar Pessoa Física;
- Cadastrar Pessoa Jurídica;
- Listar todos os clientes;
- Buscar clientes por nome;
- Atualizar telefone e e-mail;
- Remover clientes pelo ID;
- Visualizar estatísticas de clientes;
- Validar CPF, CNPJ, telefone e e-mail;
- Impedir o cadastro duplicado de CPF ou CNPJ.

## ▶️ Como executar

### Pré-requisito

É necessário possuir o **JDK 17 ou superior** instalado.

### Compilar

No terminal, dentro da pasta do projeto:

```bash
javac *.java
```
### Executar

```bash
java Main
```


java Main

Cliente (classe abstrata)
│
├── PessoaFisica
│   └── cpf
│
└── PessoaJuridica
    ├── cnpj
    └── razaoSocial

CadastroClientes
└── Gerenciamento dos clientes

Main
└── Interface de console

Responsabilidade de cada classe
- Classe	Responsabilidade
- Cliente	Armazena atributos e comportamentos comuns aos clientes, como ID, nome, telefone e e-mail.
- PessoaFisica	Representa um cliente Pessoa Física e adiciona o CPF.
- PessoaJuridica	Representa um cliente Pessoa Jurídica e adiciona CNPJ e razão social.
- CadastroClientes	Gerencia a lista de clientes e as operações de cadastro, busca, atualização e remoção.
- Main	Contém o menu interativo e realiza a comunicação entre o usuário e o sistema.

* Conceitos de Programação Orientada a Objetos
Encapsulamento

Os atributos das classes são privados (private) e seu acesso ocorre por meio de getters e setters.

Os setters também realizam validações antes de aceitar determinados valores.

Exemplos:

O e-mail precisa possuir @;
O telefone deve possuir 10 ou 11 dígitos;
CPF e CNPJ precisam possuir a quantidade esperada de dígitos.

Isso ajuda a impedir que objetos sejam armazenados com dados inválidos.

Herança

As classes PessoaFisica e PessoaJuridica herdam de Cliente:
- PessoaFisica extends Cliente
- PessoaJuridica extends Cliente

Dessa forma, atributos e comportamentos comuns, como nome, telefone e e-mail, não precisam ser implementados novamente.

Cada subclasse adiciona apenas suas características específicas:

PessoaFisica → CPF;
PessoaJuridica → CNPJ e razão social.
Polimorfismo

A classe Cliente declara comportamentos que podem possuir implementações diferentes nas subclasses.

Entre eles:

getDocumento()
getTipo()

O sistema pode trabalhar com uma coleção:

List<Cliente>

contendo tanto objetos PessoaFisica quanto PessoaJuridica.

Ao executar métodos dos objetos, o Java determina em tempo de execução qual implementação deve ser utilizada de acordo com o tipo real do cliente.

PessoaJuridica também sobrescreve exibirDados() para apresentar a razão social, reutilizando o comportamento da superclasse através de:
super.exibirDados();

Reutilização e modularidade

Validações comuns, como telefone e e-mail, ficam concentradas na classe Cliente.

Assim, essas regras podem ser reutilizadas por PessoaFisica e PessoaJuridica, reduzindo duplicação de código.

💻 Conceitos de Lógica de Programação

Durante o desenvolvimento foram utilizados conceitos fundamentais de lógica de programação.

Variáveis: utilizadas para armazenar informações e controlar o funcionamento do programa, como continuar, opcao e id.

Estruturas condicionais: if/else são utilizados principalmente nas validações, enquanto switch controla as opções do menu.

Estruturas de repetição: while mantém o menu em execução e estruturas como for permitem percorrer a lista de clientes.

Métodos: as diferentes responsabilidades foram separadas em métodos, como cadastro, listagem, busca e remoção, evitando concentrar toda a lógica em um único bloco.

Tratamento de erros: try/catch, IllegalArgumentException e IllegalStateException são utilizados para tratar dados inválidos e situações como CPF ou CNPJ duplicados sem encerrar inesperadamente o programa.

🧪 Testes realizados

O projeto foi compilado utilizando javac e suas principais funcionalidades foram testadas através do menu.

Cenário	Resultado esperado
Carregar dados de teste	Clientes PF e PJ cadastrados corretamente
Listar clientes	Todos os clientes são apresentados
Telefone inválido	Cadastro rejeitado
CPF incompleto	Cadastro rejeitado
CPF/CNPJ duplicado	Cadastro rejeitado
Buscar por nome	Apenas clientes correspondentes são apresentados
Atualizar contato	Novos dados aparecem nas consultas seguintes
Remover cliente	Cliente deixa de aparecer na listagem
Visualizar estatísticas	Quantidade total, PF e PJ apresentada corretamente
🎯 Resultado

A aplicação centraliza o cadastro de clientes em um único sistema e utiliza validações para reduzir informações incorretas.

Também evita clientes duplicados através da verificação de CPF e CNPJ e diferencia Pessoa Física de Pessoa Jurídica utilizando herança e polimorfismo, evitando duplicação desnecessária de código.

O projeto demonstra na prática conceitos fundamentais de:

Lógica de Programação;
Java;
Programação Orientada a Objetos;
Encapsulamento;
Herança;
Polimorfismo;
Tratamento de exceções;
Organização e reutilização de código.

