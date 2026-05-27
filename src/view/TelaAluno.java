package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controller.AlunoController;

public class TelaAluno extends JFrame {
	
	private static final long serialVersionUID = 1L;

    // 1. Atributos - Todos os 15 campos do mockup
    public JTextField txtId, txtNome, txtCpf, txtRg, txtCep, txtEndereco, txtNumero, 
                      txtBairro, txtCidade, txtUf, txtComplemento, txtTelefone, 
                      txtCelular, txtEmail, txtDataNascimento;
    
    public JButton btnSalvar, btnLimpar, btnExcluir;
    public JTable tabelaAlunos;
    public DefaultTableModel modeloTabela;
    private AlunoController controller;

    public TelaAluno() {
        setTitle("Gerenciamento de Clientes - Sistema Corporativo");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        

        // --- PAINEL NORTE: FORMULÁRIO ---
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        // Linha 1: ID, Nome, Data Nascimento
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtId = new JTextField(5); txtId.setEditable(false);
        txtNome = new JTextField(25);
        txtDataNascimento = new JTextField(10);
        linha1.add(new JLabel("ID:")); linha1.add(txtId);
        linha1.add(new JLabel("Nome:")); linha1.add(txtNome);
        linha1.add(new JLabel("Data Nasc:")); linha1.add(txtDataNascimento);

        // Linha 2: CPF, RG
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCpf = new JTextField(12);
        txtRg = new JTextField(12);
        linha2.add(new JLabel("CPF:")); linha2.add(txtCpf);
        linha2.add(new JLabel("RG:")); linha2.add(txtRg);

        // Linha 3: CEP, Endereço, Número
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCep = new JTextField(8);
        txtEndereco = new JTextField(25);
        txtNumero = new JTextField(5);
        linha3.add(new JLabel("CEP:")); linha3.add(txtCep);
        linha3.add(new JLabel("Endereço:")); linha3.add(txtEndereco);
        linha3.add(new JLabel("Nº:")); linha3.add(txtNumero);

        // Linha 4: Bairro, Cidade, UF, Complemento
        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBairro = new JTextField(12);
        txtCidade = new JTextField(12);
        txtUf = new JTextField(3);
        txtComplemento = new JTextField(10);
        linha4.add(new JLabel("Bairro:")); linha4.add(txtBairro);
        linha4.add(new JLabel("Cidade:")); linha4.add(txtCidade);
        linha4.add(new JLabel("UF:")); linha4.add(txtUf);
        linha4.add(new JLabel("Compl:")); linha4.add(txtComplemento);

        // Linha 5: Telefone, Celular, Email
        JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTelefone = new JTextField(12);
        txtCelular = new JTextField(12);
        txtEmail = new JTextField(25);
        linha5.add(new JLabel("Tel:")); linha5.add(txtTelefone);
        linha5.add(new JLabel("Cel:")); linha5.add(txtCelular);
        linha5.add(new JLabel("Email:")); linha5.add(txtEmail);

        // Adiciona todas as linhas ao painel de formulário
        painelFormulario.add(linha1);
        painelFormulario.add(linha2);
        painelFormulario.add(linha3);
        painelFormulario.add(linha4);
        painelFormulario.add(linha5);

        // --- PAINEL CENTRAL: BOTÕES E TABELA ---
        JPanel painelCentral = new JPanel(new BorderLayout());
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSalvar = new JButton("Salvar/Atualizar");
        btnLimpar = new JButton("Limpar");
        btnExcluir = new JButton("Excluir");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);

        // Tabela
        String[] colunas = {"ID", "Nome", "CPF", "Celular", "Cidade", "Email"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaAlunos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaAlunos);

        painelCentral.add(painelBotoes, BorderLayout.NORTH);
        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        // Adiciona os painéis principais ao frame
        add(painelFormulario, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);

     // Inicializa o controller
        this.controller = new AlunoController(this);
    }
}
