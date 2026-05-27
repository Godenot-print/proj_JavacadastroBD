package dao;

import model.Cliente;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // 1. SALVAR (CREATE)
    public void salvar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        // Tabela alterada para 'cliente'
        String sql = "INSERT INTO cliente (nome, sobrenome, nomeM, nomeP, cpf, data_nascimento, endereco, cep) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao(); 
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getNomeM());
            stmt.setString(4, cliente.getNomeP());
            stmt.setString(5, cliente.getCPF());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getCEP());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar no banco: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, null);
        }
    }

    // 2. ATUALIZAR (UPDATE)
    public void atualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE cliente SET nome=?, sobrenome=?, nomeM=?, nomeP=?, cpf=?, data_nascimento=?, endereco=?, cep=? WHERE id=?";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getNomeM());
            stmt.setString(4, cliente.getNomeP());
            stmt.setString(5, cliente.getCPF());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getCEP());
            stmt.setInt(9, cliente.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar no banco: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, null);
        }
    }

    // 3. LISTAR (READ ALL)
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente ORDER BY id DESC";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente a = new Cliente();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setSobrenome(rs.getString("sobrenome"));
                a.setNomeM(rs.getString("nomeM"));
                a.setNomeP(rs.getString("nomeP"));
                a.setCPF(rs.getString("cpf"));
                a.setDataNascimento(rs.getString("data_nascimento"));
                a.setEndereco(rs.getString("endereco"));
                a.setCEP(rs.getString("cep"));
                lista.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, rs);
        }
        return lista;
    }

    // 4. EXCLUIR (DELETE)
    public void excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM cliente WHERE id = ?"; 
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir no banco: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, null);
        }
    }

    // 5. BUSCAR POR ID (Para preencher o formulário)
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente a = new Cliente();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setSobrenome(rs.getString("sobrenome"));
                a.setNomeM(rs.getString("nomeM"));
                a.setNomeP(rs.getString("nomeP"));
                a.setCPF(rs.getString("cpf"));
                a.setDataNascimento(rs.getString("data_nascimento"));
                a.setEndereco(rs.getString("endereco"));
                a.setCEP(rs.getString("cep"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, stmt, rs);
        }
        return a;
    }

    private void fecharConexoes(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}