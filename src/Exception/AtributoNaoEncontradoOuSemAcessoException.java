package Exception;

public class AtributoNaoEncontradoOuSemAcessoException extends RuntimeException{
//Runtime = não chekada
//Exception = chekada
//Chekada obriga o dev tratar a exceção, EXCEPTION é em casos extremos
    public AtributoNaoEncontradoOuSemAcessoException() {
        super();
    }

    public AtributoNaoEncontradoOuSemAcessoException(String message) {
        super(message);
    }
}
