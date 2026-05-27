package dao;

import model.Aluno;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // 1. SALVAR (CREATE)
    public void salvar(Aluno aluno) {
        Connection conn = null;
        PreparedStatement stmt = null;

        // Tabela alterada para 'aluno'
        String sql = "INSERT INTO aluno (nome, cpf, rg, cep, endereco, numero, bairro, cidade, uf, complemento, celular, telefone, email, data_nascimento) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao(); 
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getRg());
            stmt.setString(4, aluno.getCep());
            stmt.setString(5, aluno.getEndereco());
            stmt.setString(6, aluno.getNumero());
            stmt.setString(7, aluno.getBairro());
            stmt.setString(8, aluno.getCidade());
            stmt.setString(9, aluno.getUf());
            stmt.setString(10, aluno.getComplemento());
            stmt.setString(11, aluno.getCelular());
            stmt.setString(12, aluno.getTelefone());
            stmt.setString(13, aluno.getEmail());
            stmt.setString(14, aluno.getDataNascimento());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar no banco: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, null);
        }
    }

    // 2. ATUALIZAR (UPDATE)
    public void atualizar(Aluno aluno) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE aluno SET nome=?, cpf=?, rg=?, cep=?, endereco=?, numero=?, bairro=?, cidade=?, uf=?, complemento=?, celular=?, telefone=?, email=?, data_nascimento=? WHERE id=?";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getRg());
            stmt.setString(4, aluno.getCep());
            stmt.setString(5, aluno.getEndereco());
            stmt.setString(6, aluno.getNumero());
            stmt.setString(7, aluno.getBairro());
            stmt.setString(8, aluno.getCidade());
            stmt.setString(9, aluno.getUf());
            stmt.setString(10, aluno.getComplemento());
            stmt.setString(11, aluno.getCelular());
            stmt.setString(12, aluno.getTelefone());
            stmt.setString(13, aluno.getEmail());
            stmt.setString(14, aluno.getDataNascimento());
            stmt.setInt(15, aluno.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar no banco: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, null);
        }
    }

    // 3. LISTAR (READ ALL)
    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM aluno ORDER BY id DESC";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                a.setRg(rs.getString("rg"));
                a.setCep(rs.getString("cep"));
                a.setEndereco(rs.getString("endereco"));
                a.setNumero(rs.getString("numero"));
                a.setBairro(rs.getString("bairro"));
                a.setCidade(rs.getString("cidade"));
                a.setUf(rs.getString("uf"));
                a.setComplemento(rs.getString("complemento"));
                a.setCelular(rs.getString("celular"));
                a.setTelefone(rs.getString("telefone"));
                a.setEmail(rs.getString("email"));
                a.setDataNascimento(rs.getString("data_nascimento"));
                lista.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        } finally {
            fecharConexoes(conn, stmt, rs);
        }
        return lista;
    }

    // 4. EXCLUIR (DELETE)
    public void excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM aluno WHERE id = ?"; 
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
    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno a = new Aluno();
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
                a.setCpf(rs.getString("cpf"));
                a.setRg(rs.getString("rg"));
                a.setCep(rs.getString("cep"));
                a.setEndereco(rs.getString("endereco"));
                a.setNumero(rs.getString("numero"));
                a.setBairro(rs.getString("bairro"));
                a.setCidade(rs.getString("cidade"));
                a.setUf(rs.getString("uf"));
                a.setComplemento(rs.getString("complemento"));
                a.setTelefone(rs.getString("telefone"));
                a.setCelular(rs.getString("celular"));
                a.setEmail(rs.getString("email"));
                a.setDataNascimento(rs.getString("data_nascimento"));
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
