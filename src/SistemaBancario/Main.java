package SistemaBancario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static CRUDCliente crudCliente = new CRUDCliente();
    private static CRUDConta crudConta = new CRUDConta();


    public static void main(String[] args) {

        Cliente cliente = criarCliente();
        CRUDCliente.salvar(cliente);
        Conta conta = interacaoAddConta(cliente);
        CRUDConta.salvar(conta);

        System.out.println(cliente);
        System.out.println(conta);

        /*

        do {
            System.out.print("1 - Criar\n2 - Editar\n3 - Buscar Por Número da Conta\n4 - Buscar Tudo\n5 - Deletar \n6 - Sair\nR: ");
            int number = sc.nextInt();

            if (number == 1) {
                interacaoAddConta();
            } else if (number == 2) {
                interacaoUpdate();
            } else if (number == 3) {
                interacaoBuscar();
            } else if (number == 4) {
                System.out.println(buscarTudo());
            } else if (number == 5) {
                interacaoDELETE();
            } else {
                System.exit(0);
            }
        } while (true);
         */
    }
    
    private static Cliente criarCliente() {
        System.out.print("CPF:");
        String cpf = sc.nextLine();

        System.out.print("Nome:");
        String nome = sc.nextLine();
        return new Cliente(nome, cpf);
    }

    private static Conta interacaoAddConta(Cliente titular) {
        System.out.print("Número Conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.print("Saldo: ");
        double saldo = sc.nextDouble();

        System.out.print("Limite: ");
        double limite = sc.nextDouble();

        return new Conta(numeroConta, titular, saldo, limite);
    }

     /*
    private static void interacaoDELETE() {
        System.out.println("Deletar Pelo número");
        System.out.println("Digite o número que você quer deletar");
        Conta conta = buscar(sc.nextInt());
        sc.nextLine();

        delete(conta.getNumeroConta());
    }

    private static void interacaoBuscar() {
        System.out.println("Buscar Pelo número");
        System.out.println("Digite o número que você quer buscar");
        int numero = sc.nextInt();

        Conta conta = buscar(numero);
        System.out.println(conta);
    }



    public static void interacaoUpdate() {
        System.out.println("\nEDITAR CONTA");

        System.out.print("Número Conta: ");
        Conta conta = buscar(sc.nextInt());
        sc.nextLine();

        System.out.println("EDITAR CONTA");
        System.out.print("Nome: ");
        String titularEdi = sc.nextLine();

        System.out.print("Saldo: ");
        double saldoEdi = sc.nextDouble();

        System.out.print("Limite: ");
        double limiteEdi = sc.nextDouble();

        editarConta(titularEdi, saldoEdi, limiteEdi, conta.getNumeroConta());
    }
    */

}
