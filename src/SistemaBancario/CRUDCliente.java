package SistemaBancario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDCliente {

    public static void salvar(Cliente cliente){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_cliente (nome, cpf) VALUES (?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.execute();

            cliente.setId(atualizarID(cliente));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int atualizarID(Cliente cliente) {

        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT id FROM tb_cliente where nome = ? and cpf = ?");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("DEU RUIM BRO!");
    }

    public static Cliente buscarPorId(int id){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

    public static List<Cliente> buscarTudo(){
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConexaoBanco.getConnection()){

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

    public static void delete(int id){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_cliente where id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editar(int id, String nome, String cpf){

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
