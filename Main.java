import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CadastroClientes cadastro = new CadastroClientes();

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        boolean continuar = true; // variável de controle do laço

        System.out.println("=========================================");
        System.out.println(" SISTEMA DE CADASTRO DE CLIENTES ");
        System.out.println("=========================================");

        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarPessoaFisica();
                case 2 -> cadastrarPessoaJuridica();
                case 3 -> listarClientes();
                case 4 -> buscarPorNome();
                case 5 -> atualizarContato();
                case 6 -> removerCliente();
                case 7 -> exibirEstatisticas();
                case 8 -> carregarDadosDeTeste();
                case 0 -> {
                    continuar = false;
                    System.out.println("Encerrando o sistema. Até logo!");
                }
                default -> System.out.println(">> Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    // ---------------------- FUNÇÕES AUXILIARES DO MENU ----------------------

    private static void exibirMenu() {
        System.out.println("\n----------------- MENU -----------------");
        System.out.println("1 - Cadastrar Pessoa Física");
        System.out.println("2 - Cadastrar Pessoa Jurídica");
        System.out.println("3 - Listar todos os clientes");
        System.out.println("4 - Buscar cliente por nome");
        System.out.println("5 - Atualizar telefone/e-mail");
        System.out.println("6 - Remover cliente por ID");
        System.out.println("7 - Estatísticas do cadastro");
        System.out.println("8 - Carregar dados de teste (demonstração)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        // Tratamento simples de erro: se o usuário digitar algo que não é
        // número, o programa não quebra, apenas avisa e devolve -1.
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarPessoaFisica() {
        System.out.println("\n-- Cadastro de Pessoa Física --");
        try {
            System.out.print("Nome completo: ");
            String nome = scanner.nextLine();
            System.out.print("Telefone (com DDD): ");
            String telefone = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            System.out.print("CPF (somente números ou com pontuação): ");
            String cpf = scanner.nextLine();

            PessoaFisica pf = new PessoaFisica(nome, telefone, email, cpf);
            cadastro.cadastrar(pf);
            System.out.println(">> Cliente cadastrado com sucesso! ID gerado: " + pf.getId());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(">> Erro no cadastro: " + e.getMessage());
        }
    }

    private static void cadastrarPessoaJuridica() {
        System.out.println("\n-- Cadastro de Pessoa Jurídica --");
        try {
            System.out.print("Nome fantasia: ");
            String nome = scanner.nextLine();
            System.out.print("Telefone (com DDD): ");
            String telefone = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            System.out.print("CNPJ (somente números ou com pontuação): ");
            String cnpj = scanner.nextLine();
            System.out.print("Razão Social: ");
            String razaoSocial = scanner.nextLine();

            PessoaJuridica pj = new PessoaJuridica(nome, telefone, email, cnpj, razaoSocial);
            cadastro.cadastrar(pj);
            System.out.println(">> Cliente cadastrado com sucesso! ID gerado: " + pj.getId());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(">> Erro no cadastro: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        System.out.println("\n-- Lista de Clientes Cadastrados --");
        if (cadastro.estaVazio()) {
            System.out.println(">> Nenhum cliente cadastrado ainda.");
            return;
        }
        // POLIMORFISMO em ação: cada "c" pode ser PessoaFisica ou
        // PessoaJuridica, mas o laço trata todos igualmente como Cliente.
        // exibirDados() executa a versão correta para cada tipo.
        for (Cliente c : cadastro.listarTodos()) {
            System.out.println(c.exibirDados());
        }
    }

    private static void buscarPorNome() {
        System.out.print("\nDigite parte do nome a buscar: ");
        String termo = scanner.nextLine();
        List<Cliente> encontrados = cadastro.buscarPorNome(termo);
        if (encontrados.isEmpty()) {
            System.out.println(">> Nenhum cliente encontrado com esse nome.");
        } else {
            System.out.println(">> " + encontrados.size() + " cliente(s) encontrado(s):");
            for (Cliente c : encontrados) {
                System.out.println(c.exibirDados());
            }
        }
    }

    private static void atualizarContato() {
        System.out.print("\nDigite o ID do cliente a atualizar: ");
        int id = lerOpcao();
        Cliente c = cadastro.buscarPorId(id);
        if (c == null) {
            System.out.println(">> Cliente não encontrado.");
            return;
        }
        try {
            System.out.print("Novo telefone (Enter para manter o atual): ");
            String telefone = scanner.nextLine();
            System.out.print("Novo e-mail (Enter para manter o atual): ");
            String email = scanner.nextLine();
            cadastro.atualizarContato(id, telefone, email);
            System.out.println(">> Contato atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(">> Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void removerCliente() {
        System.out.print("\nDigite o ID do cliente a remover: ");
        int id = lerOpcao();
        if (cadastro.removerPorId(id)) {
            System.out.println(">> Cliente removido com sucesso.");
        } else {
            System.out.println(">> Nenhum cliente encontrado com esse ID.");
        }
    }

    private static void exibirEstatisticas() {
        System.out.println("\n-- Estatísticas do Cadastro --");
        System.out.println("Total de clientes: " + cadastro.total());
        System.out.println("Pessoas Físicas: " + cadastro.contarPorTipo("Pessoa Física"));
        System.out.println("Pessoas Jurídicas: " + cadastro.contarPorTipo("Pessoa Jurídica"));
    }

    private static void carregarDadosDeTeste() {
        System.out.println("\n-- Carregando dados de teste --");
        try {
            cadastro.cadastrar(new PessoaFisica("Maria Souza", "(11) 98888-1234", "maria@email.com", "123.456.789-09"));
            cadastro.cadastrar(new PessoaFisica("João Pereira", "(11) 97777-5555", "joao@email.com", "987.654.321-00"));
            cadastro.cadastrar(new PessoaJuridica("Padaria Pão Quente", "(11) 3333-4444",
                    "contato@paoquente.com", "12.345.678/0001-99", "Pão Quente Alimentos LTDA"));
            System.out.println(">> 3 clientes de teste carregados com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {

            System.out.println(">> Dados de teste já haviam sido carregados (" + e.getMessage() + ")");
        }
    }
}
