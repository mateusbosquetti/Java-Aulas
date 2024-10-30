package EventoCultural.Participante;

import EventoCultural.ConexaoBanco;
import EventoCultural.Inscricao.BancoInscricao;

import java.sql.*;

public class BancoParticipante {

    public void adicionarParticipante(Participante participante) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO participante(nome, email) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, participante.getNome());
            ps.setString(2, participante.getEmail());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                participante.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Participante buscarParticipantePorEmail(String email) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM participante WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Participante(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Usuário não encontrado!");
    }

    public Participante buscarParticipantePorId(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM participante WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Participante(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Usuário não encontrado!");
    }


    public void removerParticipante(int id) {

        try (Connection connection = ConexaoBanco.getConnection()) {

            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM participante WHERE id = ?");
                ps.setInt(1, id);
                ps.execute();
            } catch (SQLIntegrityConstraintViolationException e) {
                BancoInscricao bancoInscricao = new BancoInscricao();
                bancoInscricao.removerInscricao(bancoInscricao.buscarInscricaoPeloParticipante(id).getId());
                removerParticipante(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
