package SistemaBancario;

import SistemaBancario.Exceptions.ContaInexistenteException;
import SistemaBancario.Exceptions.ContaPropriaException;
import SistemaBancario.Exceptions.SaldoInsuficienteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        do {

            System.out.println("1 - Criar\n2 - Editar\n3 - Buscar Por Nome\n4 - Buscar Tudo\n5 - Deletar \n6 - Sair\nR: ");
            int number = sc.nextInt();

            if (number == 1) {
                criarConta();
            } else if (number == 2) {
                updateConta();
            } else if (number == 3) {
                buscarPeloNumero();
            } else if (number == 4) {
                System.out.println(buscarTudo());}
            else if (number == 5) {
                deletarConta();
            } else {
                System.exit(0);
            }
        } while (true);
    }

    private static void deletarConta() {
        System.out.println("Deletar Pelo numero");
        System.out.println("Digite o núemro que você quer deletar");
        int numero = sc.nextInt();

        delete(numero);
    }

    private static void delete(int numero) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_conta WHERE numeroConta = ?");
            ps.setInt(1, numero);
            ps.execute();

        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
    }

    private static void buscarPeloNumero() {
        System.out.println("Buscar Pelo numero");
        System.out.println("Digite o núemro que você quer buscar");
        int numero = sc.nextInt();

        Conta conta = buscar(numero);
        System.out.println(conta);
    }

    private static void criarConta() {
        System.out.print("Número Conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String titular = sc.nextLine();

        System.out.print("Saldo: ");
        double saldo = sc.nextDouble();

        System.out.print("Limite: ");
        double limite = sc.nextDouble();

        Conta conta = new Conta(numeroConta, titular, saldo, limite);
        salvar(conta);
    }

    public static void updateConta() {
        System.out.println("\nEDITAR CONTA");

        System.out.print("Número Conta: ");
        int numeroContaEdi = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String titularEdi = sc.nextLine();

        System.out.print("Saldo: ");
        double saldoEdi = sc.nextDouble();

        System.out.print("Limite: ");
        double limiteEdi = sc.nextDouble();

        editarConta(titularEdi, saldoEdi, limiteEdi, numeroContaEdi);
    }

    private static Conta buscar(int numero) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_conta where numeroConta = ?");
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String titular = rs.getString("titular");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");

                return new Conta(id, 3, titular, saldo, limite);
            }
            throw new RuntimeException("A conta nao existe");
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
        throw new RuntimeException("A conexao deu ruim");
    }

    private static List<Conta> buscarTudo() {
        List<Conta> retorno = new ArrayList<>();
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_conta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int numeroConta = rs.getInt("numeroConta");
                String titular = rs.getString("titular");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");

                Conta conta = new Conta(id, numeroConta, titular, saldo, limite);
                retorno.add(conta);
            }
            return retorno;
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
        throw new RuntimeException("A conexao deu ruim");
    }


    private static void salvar(Conta conta) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_conta (id, numeroConta, titular, saldo, limite) " +
                    "VALUES (?,?,?,?,?)");

            ps.setString(1, String.valueOf(conta.getId()));
            ps.setInt(2, conta.getNumeroConta());
            ps.setString(3, conta.getTitular());
            ps.setDouble(4, conta.getSaldo());
            ps.setDouble(5, conta.getLimite());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
    }

    private static void editarConta(String titular, double saldo, double limite, int numeroConta) {

        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("update tb_conta set titular = ?, limite = ?, saldo = ? where numeroConta = ?");
            ps.setString(1, titular);
            ps.setDouble(2, limite);
            ps.setDouble(3, saldo);
            ps.setInt(4, numeroConta);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DEU RUIM O UPDATE!");
        }

    }

}
