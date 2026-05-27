package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Configurações do Banco de Dados
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/CadCli_DataBase"; // Mude 'projeto' para o nome do seu banco
    private static final String USER = "root"; // Usuário padrão do XAMPP/WAMP
    private static final String PASS = ""; // Senha padrão do XAMPP é vazia

    /**
     * Método para abrir conexão com o MySQL
     */
    public static Connection getConexao() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver"); 
            
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MySQL não encontrado! Verifique se o .jar está no Build Path.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}