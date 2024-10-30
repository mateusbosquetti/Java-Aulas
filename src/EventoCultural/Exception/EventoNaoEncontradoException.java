package EventoCultural.Exception;

public class EventoNaoEncontradoException extends RuntimeException {

    public EventoNaoEncontradoException() {
        super();
    }

    public EventoNaoEncontradoException(String message) {
        super(message);
    }
}
