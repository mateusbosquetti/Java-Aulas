package EventoCultural;

import EventoCultural.Evento.BancoEvento;
import EventoCultural.Evento.Evento;
import EventoCultural.Inscricao.BancoInscricao;
import EventoCultural.Inscricao.Inscricao;
import EventoCultural.Participante.BancoParticipante;
import EventoCultural.Participante.Participante;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    static BancoParticipante bancoParticipante = new BancoParticipante();
    static BancoEvento bancoEvento = new BancoEvento();
    static BancoInscricao bancoInscricao = new BancoInscricao();
    public static void main(String[] args) {

        Participante participanteCriado = new Participante("Mateus", "mateushb123@gmail.com");
        bancoParticipante.adicionarParticipante(participanteCriado);

        Evento eventoCriado = new Evento("Evento Halloween", "Galpão do Meyer", "31/10/2024", "Vir fantasiado!");
        bancoEvento.adicionarEvento(eventoCriado);

        Inscricao inscricaoCriada = new Inscricao(participanteCriado, eventoCriado);
        bancoInscricao.inscreverParticipante(inscricaoCriada);


    }
}
