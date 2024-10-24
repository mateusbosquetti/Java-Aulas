package SistemaBancario;

import SistemaBancario.Exceptions.*;

import java.util.HashMap;
import java.util.UUID;

public class Conta {

    private UUID id;
    private int numeroConta;
    private Cliente titular;
    private double saldo;
    private double limite;

    //ADD Usuario
    public Conta(int numeroConta, Cliente titular, double saldo, double limite) {
        this.id = UUID.randomUUID();
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.limite = limite;
    }

    //Listar Usuario
    public Conta(String id, int number, Cliente titular, double saldo, double limite) {
        this.id = UUID.fromString(id);
        this.numeroConta = number;
        this.titular = titular;
        this.saldo = saldo;
        this.limite = limite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void saque(double valorDeSaque) throws ValorInvalidoException, LimiteExcedidoException, SaldoInsuficienteException {

        validaValor(valorDeSaque);
        validaSaldo(valorDeSaque);
        validaLimite(valorDeSaque);

        this.saldo -= valorDeSaque;
    }

    public void deposito(double valorDeDeposito) throws ValorInvalidoException, LimiteExcedidoException, SaldoInsuficienteException {
        validaValor(valorDeDeposito);
        this.saldo += valorDeDeposito;
    }

    public void transferencia(double valorDeTransferencia, Conta contaATransferir) throws ValorInvalidoException, LimiteExcedidoException, SaldoInsuficienteException, ContaInexistenteException, ContaPropriaException {
        validaConta(contaATransferir);
        saque(valorDeTransferencia);
        contaATransferir.deposito(valorDeTransferencia);
    }



    private void validaValor(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
    }

    private void validaSaldo(double valor) throws SaldoInsuficienteException {
        if (this.saldo < valor) {
            throw new SaldoInsuficienteException();
        }
    }

    private void validaLimite(double valor) throws LimiteExcedidoException {
        if (this.limite < valor) {
            throw new LimiteExcedidoException();
        }
    }

    private void validaConta(Conta conta) throws ContaInexistenteException, ContaPropriaException {
        if (conta == null) {
            throw new ContaInexistenteException();
        }
        if (conta.equals(this)){
            throw new ContaPropriaException();
        }
    }


    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numeroConta=" + numeroConta +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';
    }

    public String toStringSemCliente() {
        return "Conta{" +
                "id=" + id +
                ", numeroConta=" + numeroConta +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';
    }
}
