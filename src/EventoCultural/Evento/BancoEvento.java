package EventoCultural.Evento;


import EventoCultural.ConexaoBanco;

import java.sql.*;

public class BancoEvento {

    public void adicionarEvento (Evento evento){

        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO evento (nome, local, data, descricao) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setString(3, evento.getData());
            ps.setString(4, evento.getDescricao());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                evento.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Evento buscarEventoPorNome(String nome){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM evento WHERE nome = ?");
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Evento(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Evento não encontrado!");
    }

    public void removerEvento(int id){
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM evento WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
