package UUIDtest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class uuid {
    public static void main(String[] args) {
        System.out.println("Inicio dos testes");
        int contador = 0;

        List<String> uuids = new ArrayList<>();
        String uuidGerado = "";

        long inicio = System.nanoTime();

        while (!uuids.contains(uuidGerado)) {

            uuidGerado = String.valueOf(UUID.randomUUID());
            uuids.add(uuidGerado);
            System.out.println(uuidGerado);
            contador++;

        }
        long fim = System.nanoTime();
        long duracao = fim - inicio;

        System.out.println("Testte finalizado tentativas: " + contador);
        System.out.println("Duração: " + duracao);

    }

}
