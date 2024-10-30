package EventoCultural.Exception;

public class ParticipanteNaoEncontradoException extends RuntimeException {

    public ParticipanteNaoEncontradoException() {
        super();
    }

    public ParticipanteNaoEncontradoException(String message) {
        super(message);
    }
}
