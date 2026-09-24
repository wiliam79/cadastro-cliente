import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar a coleção de clientes: cadastrar,
 * listar, buscar, atualizar e remover.
 *
 * Ela concentra as regras de negócio (lógica de programação) e mantém a
 * lista de objetos Cliente encapsulada (privada), expondo apenas métodos
 * controlados — outra aplicação prática de encapsulamento, agora no nível
 * da coleção, não só do objeto individual.
 */
public class CadastroClientes {

    private final List<Cliente> clientes = new ArrayList<>();

    /**
     * Adiciona um novo cliente à coleção, desde que o documento (CPF/CNPJ)
     * ainda não esteja cadastrado.
     */
    public void cadastrar(Cliente cliente) {
        if (buscarPorDocumento(cliente.getDocumento()) != null) {
            throw new IllegalStateException(
                "Já existe um cliente cadastrado com o documento " + cliente.getDocumento());
        }
        clientes.add(cliente);
    }

    /**
     * Remove um cliente pelo ID. Retorna true se encontrou e removeu,
     * false caso contrário.
     */
    public boolean removerPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca clientes cujo nome contenha o texto informado (case-insensitive).
     * Demonstra o uso de laço + condição para filtrar uma lista.
     */
    public List<Cliente> buscarPorNome(String textoBusca) {
        List<Cliente> resultado = new ArrayList<>();
        String alvo = textoBusca.toLowerCase();
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(alvo)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Busca um cliente por ID exato. Retorna null se não encontrar.
     */
    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca um cliente pelo documento (CPF ou CNPJ, já formatado).
     * Usado internamente para evitar cadastros duplicados.
     */
    public Cliente buscarPorDocumento(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Atualiza telefone e/ou e-mail de um cliente já cadastrado.
     * Reaproveita a validação já existente nos setters da classe Cliente
     * (reutilização de código).
     */
    public boolean atualizarContato(int id, String novoTelefone, String novoEmail) {
        Cliente c = buscarPorId(id);
        if (c == null) {
            return false;
        }
        if (novoTelefone != null && !novoTelefone.trim().isEmpty()) {
            c.setTelefone(novoTelefone);
        }
        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            c.setEmail(novoEmail);
        }
        return true;
    }

    /**
     * Retorna quantos clientes estão cadastrados no total.
     */
    public int total() {
        return clientes.size();
    }

    /**
     * Retorna a contagem de clientes agrupada por tipo (PF / PJ).
     * Exemplo simples de uso de condição dentro de laço para totalizar dados.
     */
    public int contarPorTipo(String tipo) {
        int contador = 0;
        for (Cliente c : clientes) {
            if (c.getTipo().equalsIgnoreCase(tipo)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Retorna a lista completa (cópia) de clientes cadastrados, já ordenada
     * por ID. Devolver uma cópia protege a lista interna de alterações
     * externas indevidas — mais um reforço do encapsulamento.
     */
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public boolean estaVazio() {
        return clientes.isEmpty();
    }
}
