package SistemaBancario;

import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private Conta conta;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(int id, String nome, String cpf) {
        this(nome, cpf);
        this.id = id;
    }

    public Cliente(int id, String nome, String cpf, Conta conta) {
        this(id, nome, cpf);
        this.conta = conta;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Conta getConta() {
        return conta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", conta=" + conta +
                '}';
    }
}
