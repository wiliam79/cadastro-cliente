import java.util.ArrayList;
import java.util.List;



public class CadastroClientes {

    private final List<Cliente> clientes = new ArrayList<>();


    public void cadastrar(Cliente cliente) {
        if (buscarPorDocumento(cliente.getDocumento()) != null) {
            throw new IllegalStateException(
                    "Já existe um cliente cadastrado com o documento " + cliente.getDocumento());
        }
        clientes.add(cliente);
    }


    public boolean removerPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }


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


    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }



    public Cliente buscarPorDocumento(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }
        }
        return null;
    }


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


    public int total() {
        return clientes.size();
    }


    public int contarPorTipo(String tipo) {
        int contador = 0;
        for (Cliente c : clientes) {
            if (c.getTipo().equalsIgnoreCase(tipo)) {
                contador++;
            }
        }
        return contador;
    }


    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public boolean estaVazio() {
        return clientes.isEmpty();
    }
}

    public boolean estaVazio() {
        return clientes.isEmpty();
    }
}
