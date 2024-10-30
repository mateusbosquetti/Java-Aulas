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
}
