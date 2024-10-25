package SistemaBancario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUDCliente {

    public static void salvar(Cliente cliente) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_cliente (nome, cpf) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                cliente.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cliente buscarPorId(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                return new Cliente(idCliente, nome, cpf);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Cliente nao encontrado");
    }

    public static Cliente buscarPeloNome(String nomeDoCliente) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente where nome = ?");
            ps.setString(1, nomeDoCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                return new Cliente(idCliente, nome, cpf);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Cliente nao encontrado");
    }

    public static List<Cliente> buscarTudo() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                clientes.add(new Cliente(id, nome, cpf));
            }

            return clientes;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException("DEU RUIM BRO!");
    }

    public static void delete(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_cliente where id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            int escolha;
            Scanner sc = new Scanner(System.in);
            System.out.println("Cliente tem conta, deseja excluir a conta primeiro?\n1 - Sim\n2 - Não\nR: ");
            escolha = sc.nextInt();

            if (escolha == 1){
                CRUDConta.delete(CRUDConta.buscarPeloTitular(id).getNumeroConta());
                delete(id);
                System.out.println("Conta excluída!");
            } else {
                System.out.println("Operação cancelada!");
            }
        }
    }

    public static void editar(int id, String nome, String cpf) {

        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("UPDATE tb_cliente SET nome = ?, cpf = ? WHERE id = ?");
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setInt(3, id);
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
