package SistemaBancario;

import SistemaBancario.Exceptions.*;

import java.util.HashMap;

public class Conta {

    private int numeroConta;
    private String titular;
    private double saldo;
    private double limite;


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

    public Conta(int numeroConta, String titular, double saldo, double limite) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.limite = limite;
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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
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

    @Override
    public String toString() {
        return "Conta{" +
                "numeroConta=" + numeroConta +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';
    }
}
