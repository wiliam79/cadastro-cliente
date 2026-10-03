
public class PessoaJuridica extends Cliente {

    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica(String nome, String telefone, String email, String cnpj, String razaoSocial) {
        super(nome, telefone, email);
        setCnpj(cnpj);
        setRazaoSocial(razaoSocial);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser vazio.");
        }
        String digitos = cnpj.replaceAll("[^0-9]", "");
        if (digitos.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido. Deve conter 14 dígitos.");
        }
        this.cnpj = formatarCnpj(digitos);
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social não pode ser vazia.");
        }
        this.razaoSocial = razaoSocial.trim();
    }

    private String formatarCnpj(String d) {
        return d.substring(0, 2) + "." + d.substring(2, 5) + "." + d.substring(5, 8)
                + "/" + d.substring(8, 12) + "-" + d.substring(12, 14);
    }

    @Override
    public String getDocumento() {
        return "CNPJ " + cnpj;
    }

    @Override
    public String getTipo() {
        return "Pessoa Jurídica";
    }

    @Override
    public String exibirDados() {
        // Reaproveita a implementação da superclasse e acrescenta a razão
        // social — outro exemplo de polimorfismo (sobrescrita de método).
        return super.exibirDados() + " | Razão Social: " + razaoSocial;
    }
}
