/**
 * Subclasse que representa um cliente Pessoa Física.
 *
 * HERANÇA: "extends Cliente" faz com que PessoaFisica reaproveite id, nome,
 * telefone, email e dataCadastro sem duplicar código.
 *
 * POLIMORFISMO: implementa getDocumento() e getTipo() à sua maneira (CPF).
 */
public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String nome, String telefone, String email, String cpf) {
        super(nome, telefone, email); // reaproveita a validação da superclasse
        setCpf(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        String digitos = cpf.replaceAll("[^0-9]", "");
        if (digitos.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        this.cpf = formatarCpf(digitos);
    }

    // Função auxiliar simples de formatação (lógica de programação: laços e
    // manipulação de strings) — deixa o CPF no padrão 000.000.000-00.
    private String formatarCpf(String digitos) {
        return digitos.substring(0, 3) + "." + digitos.substring(3, 6) + "."
             + digitos.substring(6, 9) + "-" + digitos.substring(9, 11);
    }

    @Override
    public String getDocumento() {
        return "CPF " + cpf;
    }

    @Override
    public String getTipo() {
        return "Pessoa Física";
    }
}
