package SistemaBancario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDConta {

    public static void salvar(Conta conta) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_conta (id, numeroConta, id_titular, saldo, limite) " +
                    "VALUES (?,?,?,?,?)");

            ps.setString(1, String.valueOf(conta.getId()));
            ps.setInt(2, conta.getNumeroConta());
            ps.setInt(3, conta.getTitular().getId());
            ps.setDouble(4, conta.getSaldo());
            ps.setDouble(5, conta.getLimite());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
    }

    public static Conta buscar(int numero) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_conta where numeroConta = ?");
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                int id_titular = rs.getInt("id_titular");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");

                Cliente titular = CRUDCliente.buscarPorId(id_titular);
                return new Conta(id, numero, titular, saldo, limite);
            }
            throw new RuntimeException("A conta nao existe");
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
        throw new RuntimeException("A conta não foi encontrada");
    }

    public static List<Conta> buscarTudo() {
        List<Conta> retorno = new ArrayList<>();
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_conta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int numeroConta = rs.getInt("numeroConta");

                int idTitular = rs.getInt("id_titular");
                Cliente cliente = CRUDCliente.buscarPorId(idTitular);

                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");

                Conta conta = new Conta(id, numeroConta, cliente, saldo, limite);
                retorno.add(conta);
            }
            return retorno;
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
        throw new RuntimeException("A conexao deu ruim");
    }

    public static void delete(int numero) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_conta WHERE numeroConta = ?");
            ps.setInt(1, numero);
            ps.execute();

        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
    }

    public static void editarConta(Conta conta, double saldo, double limite, int id_titular) {

        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("update tb_conta set id_titular = ?, limite = ?, saldo = ? where numeroConta = ?");
            ps.setInt(1, id_titular);
            ps.setDouble(2, limite);
            ps.setDouble(3, saldo);
            ps.setInt(4, conta.getNumeroConta());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DEU RUIM O UPDATE!");
        }
    }

    public static Conta buscarPeloTitular(int idTitular) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_conta WHERE id_titular = ?");
            ps.setInt(1, idTitular);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                int numeroConta = rs.getInt("numeroConta");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");

                Cliente titular = CRUDCliente.buscarPorId(idTitular);
                return new Conta(id, numeroConta, titular, saldo, limite);
            }
            throw new RuntimeException("A conta não existe");
        } catch (Exception e) {
            System.out.println("Deu Ruim!");
        }
        throw new RuntimeException("A conta não foi encontrada");
    }

}
