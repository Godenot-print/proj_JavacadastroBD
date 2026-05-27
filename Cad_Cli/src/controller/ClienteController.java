package controller;

import dao.ClienteDAO;
import model.Cliente;
import view.TelaCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClienteController {

    private TelaCliente tela;
    private ClienteDAO dao;

    public ClienteController(TelaCliente tela) {
        this.tela = tela;
        this.dao = new ClienteDAO();
        initController();
    }

    private void initController() {
        // Configura as ações dos botões
        tela.btnSalvar.addActionListener(e -> salvarCliente());
        tela.btnLimpar.addActionListener(e -> limparCampos());
        tela.btnExcluir.addActionListener(e -> excluirCliente());

        // Ação ao clicar na tabela (Preenche os 15 campos automaticamente)
        tela.tabelaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tela.tabelaClientes.getSelectedRow() != -1) {
                preencherFormulario();
            }
        });

        carregarTabela(); // Já inicia a tela com os dados do banco
    }

    public void salvarCliente() {
        Cliente c = new Cliente();
        
        // Se o campo ID não estiver vazio, é uma atualização
        if (!tela.txtId.getText().isEmpty()) {
            c.setId(Integer.parseInt(tela.txtId.getText()));
        }

      
        c.setNome(tela.txtNome.getText());
        c.setSobrenome(tela.txtSobrenome.getText());
        c.setNomeM(tela.txtNomeM.getText());
        c.setNomeP(tela.txtNomeP.getText());
        c.setCPF(tela.txtCPF.getText());
        c.setDataNascimento(tela.txtDataNascimento.getText());
        c.setEndereco(tela.txtEndereco.getText());
        c.setCEP(tela.txtCEP.getText());
     

        if (c.getId() == 0) {
            dao.salvar(c);
            JOptionPane.showMessageDialog(tela, "Cliente salvo com sucesso!");
        } else {
            dao.atualizar(c);
            JOptionPane.showMessageDialog(tela, "Cliente atualizado com sucesso!");
        }

        limparCampos();
        carregarTabela();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tela.tabelaClientes.getModel();
        modelo.setNumRows(0); // Limpa a tabela antes de carregar
        List<Cliente> lista = dao.listar();

        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getSobrenome(),
                c.getNomeM(),
                c.getNomeP(),
                c.getCPF(),
                c.getDataNascimento(),
                c.getEndereco(),
                c.getCEP()
            });
        }
    }

    private void preencherFormulario() {
        int linha = tela.tabelaClientes.getSelectedRow();
        int id = (int) tela.tabelaClientes.getValueAt(linha, 0);
        
        // Busca o aluno completo no banco (ou na lista) pelo ID
        Cliente c = dao.buscarPorId(id); 

        // Joga os dados de volta para os JTextFields
        tela.txtId.setText(String.valueOf(c.getId()));
        tela.txtNome.setText(c.getNome());
        tela.txtSobrenome.setText(c.getSobrenome());
        tela.txtNomeM.setText(c.getNomeM());
        tela.txtNomeP.setText(c.getNomeP());
        tela.txtCPF.setText(c.getCPF());
        tela.txtDataNascimento.setText(c.getDataNascimento());
        tela.txtEndereco.setText(c.getEndereco());
        tela.txtCEP.setText(c.getCEP());
    }

    public void limparCampos() {
        tela.txtId.setText("");
        tela.txtNome.setText("");
        tela.txtSobrenome.setText("");
        tela.txtNomeM.setText("");
        tela.txtNomeP.setText("");
        tela.txtCPF.setText("");
        tela.txtDataNascimento.setText("");
        tela.txtEndereco.setText("");
        tela.txtCEP.setText("");
        tela.txtNome.requestFocus();
    }

    public void excluirCliente() {
        if (!tela.txtId.getText().isEmpty()) {
            int id = Integer.parseInt(tela.txtId.getText());
            dao.excluir(id);
            JOptionPane.showMessageDialog(tela, "Excluído com sucesso!");
            limparCampos();
            carregarTabela();
        }
    }
}