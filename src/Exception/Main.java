package Exception;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        Carro carro = new Carro();
        carro.ligar();

//        try {
//            carro.acelerar();
//        } catch (AtributoNaoEncontradoOuSemAcessoException e) {
//            System.out.println("Carro desligado!");
//        }

        carro.acelerar();

        System.out.println(carro.getKm());


    }
}
