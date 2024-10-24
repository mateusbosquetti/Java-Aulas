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

        int escolha;

        do {
            System.out.println("Bem vindo ao sistema bancario, você deseja operar em\n1 - Clientes\n2 - Contas\n3 - Sair");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    //menuConta();
                    //TODO Implementar
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (true);
    }

    private static void menuCliente() {
        System.out.println("Bem vindo ao menu dos Clientes, você deseja");
        System.out.print("1 - Criar\n2 - Editar\n3 - Buscar Por ID\n4 - Buscar Tudo\n5 - Deletar \n6 - Sair\nR: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (number == 1) {
            adicionarCliente();
        } else if (number == 2) {
            editarCliente();
        } else if (number == 3) {
            System.out.println(buscarCliente());
        } else if (number == 4) {
            System.out.println(buscarTodosOsClientes());
        } else if (number == 5) {
            deletarCliente();
        } else {
            System.exit(0);
        }
    }

    private static void adicionarCliente() {

        System.out.println("Adicionar Novo Cliente");
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Digite o cpf do cliente: ");
        String cpf = sc.nextLine();

        CRUDCliente.salvar(new Cliente(nome, cpf));
    }

    private static void deletarCliente() {

        System.out.println("Deletar Pelo número");
        System.out.println("Digite o número que você quer deletar: ");
        CRUDCliente.delete(sc.nextInt());

    }

    private static List<Cliente> buscarTodosOsClientes() {
        return CRUDCliente.buscarTudo();
    }

    private static Cliente buscarCliente() {

        System.out.println("Digite o ID do cliente que deseja buscar: ");
        try {
            return CRUDCliente.buscarPorId(sc.nextInt());
        } catch (Exception e) {
            System.out.println("ID Inexistente");
            return null;
        }
    }

    private static void editarCliente() {

        System.out.println("Digite o ID que deseja editar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o novo nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Digite o novo cpf do cliente: ");
        String cpf = sc.nextLine();

        CRUDCliente.editar(id, nome, cpf);

    }

     /*

      private static void menuConta() {

        System.out.println("Bem vindo ao menu das Contas, você deseja");
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

    }


    private static Cliente criarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();
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
