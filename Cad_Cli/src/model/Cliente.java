package model;

public class Cliente {

    // 1. Atributos (Os 15 campos da foto)
    private int id;
    private String nome;
    private String sobrenome;
    private String nomeM;
    private String nomeP;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private String cep;
   
    // 2. Construtor Vazio (Necessário para o DAO listar os dados)
    public Cliente() {
    }

    // 3. Construtor Completo (Para facilitar a criação do objeto pelo Controller)
    public Cliente(int id, String nome, String sobrenome, String nomeM, String nomeP, String cpf,  
                   String dataNascimento, String endereco, String cep) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeM = nomeM;
        this.nomeP = nomeP;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cep = cep;
    }

    // 4. Getters e Setters (As "portas" de entrada e saída de cada informação)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getNomeM() { return nomeM; }
    public void setNomeM(String nomeM) { this.nomeM = nomeM; }

    public String getNomeP() { return nomeP; }
    public void setNomeP(String nomeP) { this.nomeP = nomeP; }

    public String getCPF() { return cpf; }
    public void setCPF(String cpf) { this.cpf = cpf; }
    
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCEP() { return cep; }
    public void setCEP (String cep) { this.cep = cep; }

}