package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controller.ClienteController;

public class TelaCliente extends JFrame {
    
    private static final long serialVersionUID = 1L;

    // Atributos - Campos de texto
    public JTextField txtId, txtNome, txtSobrenome, txtNomeM, txtNomeP, txtCPF, txtDataNascimento, 
                      txtEndereco, txtCEP;
    
    // Atributos - Componentes de interface
    public JButton btnSalvar, btnLimpar, btnExcluir;
    public JTable tabelaClientes;
    public DefaultTableModel modeloTabela;
    private ClienteController controller;

    public TelaCliente() {
        // Configurações básicas da janela
        setTitle("Gerenciamento de Clientes - Sistema Corporativo");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 1. CONSTRUÇÃO DO FORMULÁRIO (PAINEL NORTE) ---
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        // Linha 1: ID, Nome, Sobrenome
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtId = new JTextField(5); txtId.setEditable(false);
        txtNome = new JTextField(25);
        txtSobrenome = new JTextField(25);
        linha1.add(new JLabel("ID:")); linha1.add(txtId);
        linha1.add(new JLabel("Nome:")); linha1.add(txtNome);
        linha1.add(new JLabel("Sobrenome:")); linha1.add(txtSobrenome);

        // Linha 2: Nome da Mãe e do Pai
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNomeM = new JTextField(25);
        txtNomeP = new JTextField(25);
        linha2.add(new JLabel("Nome da Mãe:")); linha2.add(txtNomeM);
        linha2.add(new JLabel("Nome do Pai:")); linha2.add(txtNomeP);

        // Linha 3: CPF e Data de Nascimento
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCPF = new JTextField(15);
        txtDataNascimento = new JTextField(15);
        linha3.add(new JLabel("CPF:")); linha3.add(txtCPF);
        linha3.add(new JLabel("Data de Nascimento:")); linha3.add(txtDataNascimento);
        
        // Linha 4: Endereço e CEP
        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtEndereco = new JTextField(30);
        txtCEP = new JTextField(15);
        linha4.add(new JLabel("Endereço:")); linha4.add(txtEndereco);
        linha4.add(new JLabel("CEP:")); linha4.add(txtCEP);

        painelFormulario.add(linha1);
        painelFormulario.add(linha2);
        painelFormulario.add(linha3);
        painelFormulario.add(linha4);

        // --- 2. CONSTRUÇÃO DA TABELA E BOTÕES (PAINEL CENTRAL) ---
        JPanel painelCentral = new JPanel(new BorderLayout());
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSalvar = new JButton("Salvar/Atualizar");
        btnLimpar = new JButton("Limpar");
        btnExcluir = new JButton("Excluir");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);

        // Configuração da Tabela
        String[] colunas = {"ID", "Nome", "Sobrenome", "Nome da Mãe", "Nome do Pai", "CPF", "Data de Nascimento", "Endereço", "CEP"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaClientes);

        painelCentral.add(painelBotoes, BorderLayout.NORTH);
        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        // --- 3. FINALIZAÇÃO E ATIVAÇÃO DO CONTROLLER ---
        
        // Adiciona os painéis principais ao frame
        add(painelFormulario, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);

        // Inicializa o controller e registra os eventos
        this.controller = new ClienteController(this);
        
        // Vincula os botões aos métodos do controller (Uso da variável resolve o Warning)
        btnSalvar.addActionListener(e -> controller.salvarCliente());
        btnLimpar.addActionListener(e -> controller.limparCampos());
        btnExcluir.addActionListener(e -> controller.excluirCliente());
        
    } 
}