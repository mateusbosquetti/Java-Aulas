package EventoCultural.Inscricao;
import EventoCultural.ConexaoBanco;

import java.sql.*;

public class    BancoInscricao {

    public void inscreverParticipante(Inscricao inscricao) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO inscricao (id_participante, id_evento) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscricao.getParticipante().getId());
            ps.setInt(2, inscricao.getEvento().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
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

}
