import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata Cliente.
 *
 * Representa o conjunto de dados e comportamentos comuns a qualquer cliente
 * cadastrado no sistema, independentemente de ser Pessoa Física ou Pessoa
 * Jurídica.
 *
 * CONCEITOS DE POO APLICADOS AQUI:
 *  - ENCAPSULAMENTO: todos os atributos são "private". O acesso externo só
 *    acontece através de métodos getters/setters, que também validam os
 *    dados antes de aceitá-los (isso evita que o objeto fique em um estado
 *    inválido, como um telefone vazio ou um e-mail sem "@").
 *  - HERANÇA: esta classe é a superclasse de PessoaFisica e PessoaJuridica.
 *    Ambas reaproveitam nome, telefone, email e id sem precisar reescrever
 *    esse código (reutilização).
 *  - POLIMORFISMO: o método "exibirDados()" e "getDocumento()" são
 *    "abstract" aqui. Cada subclasse implementa sua própria versão. Quando o
 *    sistema percorre uma lista de Cliente e chama exibirDados(), o Java
 *    decide em tempo de execução qual versão do método usar, de acordo com
 *    o tipo real do objeto (PessoaFisica ou PessoaJuridica).
 */
public abstract class Cliente {

    // Contador estático simples usado para gerar IDs únicos automaticamente.
    private static int proximoId = 1;

    private final int id;
    private String nome;
    private String telefone;
    private String email;
    private final LocalDate dataCadastro;

    protected Cliente(String nome, String telefone, String email) {
        this.id = proximoId++;
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        this.dataCadastro = LocalDate.now();
    }

    // ---------------------- GETTERS ----------------------

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    // ---------------------- SETTERS (COM VALIDAÇÃO) ----------------------
    // O encapsulamento aqui não é apenas "esconder" o atributo: é garantir
    // que ninguém consiga colocar o objeto em um estado inconsistente.

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public void setTelefone(String telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("O telefone não pode ser vazio.");
        }
        // Remove espaços, parênteses e traços para validar só os dígitos.
        String somenteDigitos = telefone.replaceAll("[^0-9]", "");
        if (somenteDigitos.length() < 10 || somenteDigitos.length() > 11) {
            throw new IllegalArgumentException(
                "Telefone inválido. Use DDD + número (10 ou 11 dígitos). Ex: (11) 98888-7777");
        }
        this.telefone = telefone.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("E-mail inválido. Ex: nome@dominio.com");
        }
        this.email = email.trim();
    }

    // ---------------------- MÉTODOS ABSTRATOS (POLIMORFISMO) --------------

    /**
     * Cada subclasse devolve seu documento de identificação (CPF ou CNPJ).
     */
    public abstract String getDocumento();

    /**
     * Cada subclasse devolve o rótulo do tipo de cliente ("Pessoa Física" ou
     * "Pessoa Jurídica"). Usado para exibição e para relatórios.
     */
    public abstract String getTipo();

    /**
     * Exibe os dados completos do cliente formatados para o console.
     * Implementado de forma diferente em cada subclasse (polimorfismo),
     * mas reaproveita os dados comuns por meio de toString() (reutilização).
     */
    public String exibirDados() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
            "ID: %-4d | Tipo: %-15s | Nome: %-25s | Doc: %-18s | Tel: %-15s | E-mail: %-25s | Cadastrado em: %s",
            id, getTipo(), nome, getDocumento(), telefone, email, dataCadastro.format(fmt)
        );
    }

    @Override
    public String toString() {
        return exibirDados();
    }
}
