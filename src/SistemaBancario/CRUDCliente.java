package SistemaBancario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDCliente {

    public static void salvar(Cliente cliente){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_cliente (nome, cpf) VALUES (?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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


}
