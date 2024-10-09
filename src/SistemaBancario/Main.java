package SistemaBancario;

import SistemaBancario.Exceptions.ContaInexistenteException;
import SistemaBancario.Exceptions.ContaPropriaException;
import SistemaBancario.Exceptions.SaldoInsuficienteException;

public class Main {
    public static void main(String[] args) throws SaldoInsuficienteException {

        Conta conta1 = new Conta(1, "Mateus", 1000, 500);
        Conta conta2 = new Conta(2, "Andre", 2000, 1000);

        try {
            conta1.transferencia(59, conta1);
        }  catch (ContaInexistenteException e) {
            e.printStackTrace();
        } catch (ContaPropriaException e) {
            System.out.println("COnta propria");
        }


    }
}
