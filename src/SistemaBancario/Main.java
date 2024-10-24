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
            System.out.print("Bem vindo ao sistema bancario, você deseja operar em\n1 - Clientes\n2 - Contas\n3 - Sair\nR: ");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuConta();
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

        Cliente cliente = new Cliente(nome, cpf);
        CRUDCliente.salvar(cliente);
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


    // CONTA --------------------------------------------------


    private static void menuConta() {

        System.out.println("Bem vindo ao menu das Contas, você deseja");
        System.out.print("1 - Criar\n2 - Editar\n3 - Buscar Por Número da Conta\n4 - Buscar Tudo\n5 - Deletar \n6 - Sair\nR: ");
        int number = sc.nextInt();

        if (number == 1) {
            adicionarConta();
        } else if (number == 2) {
            editarConta();
        } else if (number == 3) {
            System.out.println(buscarContaPeloNumero());
        } else if (number == 4) {
            System.out.println(buscarContas());
        } else if (number == 5) {
            deletarConta();
        } else {
            System.exit(0);
        }
    }

    private static void adicionarConta() {
        System.out.print("Número Conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.print("Saldo: ");
        double saldo = sc.nextDouble();

        System.out.print("Limite: ");
        double limite = sc.nextDouble();

        System.out.print("Digite o id do cliente: ");
        Cliente titular = CRUDCliente.buscarPorId(sc.nextInt());

        Conta conta = new Conta(numeroConta, titular, saldo, limite);
        CRUDConta.salvar(conta);
    }


    private static void deletarConta() {
        System.out.println("Deletar Pelo número");
        System.out.println("Digite o número que você quer deletar");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        CRUDConta.delete(numeroConta);
    }

    private static Conta buscarContaPeloNumero() {
        System.out.println("Buscar Pelo número");
        System.out.println("Digite o número que você quer buscar");
        int numero = sc.nextInt();
        return CRUDConta.buscar(numero);
    }

    private static List<Conta> buscarContas() {
        return CRUDConta.buscarTudo();
    }


    public static void editarConta() {
        System.out.println("\nEDITAR CONTA");

        System.out.print("Número Conta que deseja editar: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();
        Conta contaEditada = CRUDConta.buscar(numeroConta);

        System.out.print("Nome do novo titular: ");
        String titularEdi = sc.nextLine();
        Cliente titular = CRUDCliente.buscarPeloNome(titularEdi);

        System.out.print("Saldo: ");
        double saldoEdi = sc.nextDouble();

        System.out.print("Limite: ");
        double limiteEdi = sc.nextDouble();

        CRUDConta.editarConta(contaEditada, saldoEdi, limiteEdi, titular.getId());
    }

}
