package controller;

import dao.AlunoDAO;
import model.Aluno;
import view.TelaAluno;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlunoController {

    private TelaAluno tela;
    private AlunoDAO dao;

    public AlunoController(TelaAluno tela) {
        this.tela = tela;
        this.dao = new AlunoDAO();
        initController();
    }

    private void initController() {
        // Configura as ações dos botões
        tela.btnSalvar.addActionListener(e -> salvarAluno());
        tela.btnLimpar.addActionListener(e -> limparCampos());
        tela.btnExcluir.addActionListener(e -> excluirAluno());

        // Ação ao clicar na tabela (Preenche os 15 campos automaticamente)
        tela.tabelaAlunos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tela.tabelaAlunos.getSelectedRow() != -1) {
                preencherFormulario();
            }
        });

        carregarTabela(); // Já inicia a tela com os dados do banco
    }

    private void salvarAluno() {
        Aluno c = new Aluno();
        
        // Se o campo ID não estiver vazio, é uma atualização
        if (!tela.txtId.getText().isEmpty()) {
            c.setId(Integer.parseInt(tela.txtId.getText()));
        }

        // Lendo os 15 campos da tela
        c.setNome(tela.txtNome.getText());
        c.setCpf(tela.txtCpf.getText());
        c.setRg(tela.txtRg.getText());
        c.setCep(tela.txtCep.getText());
        c.setEndereco(tela.txtEndereco.getText());
        c.setNumero(tela.txtNumero.getText());
        c.setBairro(tela.txtBairro.getText());
        c.setCidade(tela.txtCidade.getText());
        c.setUf(tela.txtUf.getText());
        c.setComplemento(tela.txtComplemento.getText());
        c.setTelefone(tela.txtTelefone.getText());
        c.setCelular(tela.txtCelular.getText());
        c.setEmail(tela.txtEmail.getText());
        c.setDataNascimento(tela.txtDataNascimento.getText());

        if (c.getId() == 0) {
            dao.salvar(c);
            JOptionPane.showMessageDialog(tela, "Aluno salvo com sucesso!");
        } else {
            dao.atualizar(c);
            JOptionPane.showMessageDialog(tela, "Aluno atualizado com sucesso!");
        }

        limparCampos();
        carregarTabela();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tela.tabelaAlunos.getModel();
        modelo.setNumRows(0); // Limpa a tabela antes de carregar
        List<Aluno> lista = dao.listar();

        for (Aluno c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getCelular(),
                c.getCidade(),
                c.getEmail()
            });
        }
    }

    private void preencherFormulario() {
        int linha = tela.tabelaAlunos.getSelectedRow();
        int id = (int) tela.tabelaAlunos.getValueAt(linha, 0);
        
        // Busca o aluno completo no banco (ou na lista) pelo ID
        Aluno c = dao.buscarPorId(id); 

        // Joga os dados de volta para os JTextFields
        tela.txtId.setText(String.valueOf(c.getId()));
        tela.txtNome.setText(c.getNome());
        tela.txtCpf.setText(c.getCpf());
        tela.txtRg.setText(c.getRg());
        tela.txtCep.setText(c.getCep());
        tela.txtEndereco.setText(c.getEndereco());
        tela.txtNumero.setText(c.getNumero());
        tela.txtBairro.setText(c.getBairro());
        tela.txtCidade.setText(c.getCidade());
        tela.txtUf.setText(c.getUf());
        tela.txtComplemento.setText(c.getComplemento());
        tela.txtTelefone.setText(c.getTelefone());
        tela.txtCelular.setText(c.getCelular());
        tela.txtEmail.setText(c.getEmail());
        tela.txtDataNascimento.setText(c.getDataNascimento());
    }

    private void limparCampos() {
        tela.txtId.setText("");
        tela.txtNome.setText("");
        tela.txtCpf.setText("");
        tela.txtRg.setText("");
        tela.txtCep.setText("");
        tela.txtEndereco.setText("");
        tela.txtNumero.setText("");
        tela.txtBairro.setText("");
        tela.txtCidade.setText("");
        tela.txtUf.setText("");
        tela.txtComplemento.setText("");
        tela.txtTelefone.setText("");
        tela.txtCelular.setText("");
        tela.txtEmail.setText("");
        tela.txtDataNascimento.setText("");
        tela.txtNome.requestFocus();
    }

    private void excluirAluno() {
        if (!tela.txtId.getText().isEmpty()) {
            int id = Integer.parseInt(tela.txtId.getText());
            dao.excluir(id);
            JOptionPane.showMessageDialog(tela, "Excluído com sucesso!");
            limparCampos();
            carregarTabela();
        }
    }
}
