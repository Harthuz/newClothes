package newclothes;

import conexao.conexao;

public class NewClothes {
    private conexao con_cliente;

    // Método para conectar
    public void conectar() {
        con_cliente = new conexao();
        try {
            con_cliente.conecta();
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        NewClothes app = new NewClothes(); // Crie uma instância da classe NewClothes
        app.conectar(); // Chame o método conectar
    }
}
