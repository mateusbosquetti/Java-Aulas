package Exception;

public class Carro {

    private int km;
    private boolean ligado;

    public Carro() {
        this.km = 0;
        this.ligado = false;
    }

    public void acelerar() {
        if (ligado) {
            km += 10;
        } else {
            throw new AtributoNaoEncontradoOuSemAcessoException();
        }
    }

    public void ligar() {
        ligado = true;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
}
