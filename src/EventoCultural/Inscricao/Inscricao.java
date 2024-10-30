package EventoCultural.Inscricao;

import EventoCultural.Evento.Evento;
import EventoCultural.Participante.Participante;

public class Inscricao {


    private int id;
    private Participante participante;
    private Evento evento;

    public Inscricao(int id, Participante participante, Evento evento) {
        this.id = id;
        this.participante = participante;
        this.evento = evento;
    }

    public Inscricao(Participante participante, Evento evento) {
        this.participante = participante;
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Inscricao{" +
                "id=" + id +
                ", participante=" + participante +
                ", evento=" + evento +
                '}';
    }
}
