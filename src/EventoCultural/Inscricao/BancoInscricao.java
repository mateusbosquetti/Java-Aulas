package EventoCultural.Inscricao;

import EventoCultural.ConexaoBanco;
import EventoCultural.Evento.BancoEvento;
import EventoCultural.Participante.BancoParticipante;

import java.sql.*;

public class BancoInscricao {

    public void inscreverParticipante(Inscricao inscricao) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO inscricao (id_participante, id_evento) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscricao.getParticipante().getId());
            ps.setInt(2, inscricao.getEvento().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscricao.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerInscricao(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM inscricao WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Inscricao buscarInscricaoPeloParticipante(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM inscricao where id_participante = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                BancoEvento bancoEvento = new BancoEvento();
                BancoParticipante bancoParticipante = new BancoParticipante();
                return new Inscricao(
                        rs.getInt(1),
                        bancoParticipante.buscarParticipantePorId(rs.getInt(2)),
                        bancoEvento.buscarEventoPorId(rs.getInt(3))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Inscricao não encontrada!");
    }

    public Inscricao buscarInscricaoPeloEvento(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM inscricao where id_evento = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                BancoEvento bancoEvento = new BancoEvento();
                BancoParticipante bancoParticipante = new BancoParticipante();
                return new Inscricao(
                        rs.getInt(1),
                        bancoParticipante.buscarParticipantePorId(rs.getInt(2)),
                        bancoEvento.buscarEventoPorId(rs.getInt(3))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Inscricao não encontrada!");
    }


}
