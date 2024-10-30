package EventoCultural;

import EventoCultural.Evento.BancoEvento;
import EventoCultural.Evento.Evento;
import EventoCultural.Exception.EventoNaoEncontradoException;
import EventoCultural.Exception.InscricaoNaoEncontradaException;
import EventoCultural.Exception.ParticipanteNaoEncontradoException;
import EventoCultural.Inscricao.BancoInscricao;
import EventoCultural.Inscricao.Inscricao;
import EventoCultural.Participante.BancoParticipante;
import EventoCultural.Participante.Participante;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static BancoParticipante bancoParticipante = new BancoParticipante();
    static BancoEvento bancoEvento = new BancoEvento();
    static BancoInscricao bancoInscricao = new BancoInscricao();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int escolha;

        System.out.println("Bem vindo ao sistema de Eventos Culturais");
        while (true) {

            System.out.print("Você deseja operar em qual setor?\n1 - Eventos\n2 - Participantes\n3 - Inscrições\n4 - Sair\nR: ");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    eventoMenu();
                    break;
                case 2:
                    participanteMenu();
                    break;
                case 3:
                    inscricaoMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    //INSCRICAO

    private static void inscricaoMenu() {
        System.out.println("Menu Inscricao");
        System.out.print("Você deseja realizar qual ação?\n1 - Criar\n2 - Buscar\n3 - Deletar\n4 - Voltar\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                criarInscricao();
                break;
            case 2:
                buscarInscricao();
                break;
            case 3:
                deletarInscricao();
                break;
            case 4:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void deletarInscricao() {
        System.out.print("ID: ");
        bancoInscricao.removerInscricao(sc.nextInt());
    }

    private static void buscarInscricao() {
        System.out.print("1 - Buscar pelo ID\n2 - Buscar pelo Participante\n3 - Buscar pelo Evento\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        try {
            switch (escolha) {
                case 1:
                    System.out.print("Id: ");
                    System.out.println(bancoInscricao.buscarInscricaoPeloId(sc.nextInt()));
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Id do Participante: ");
                    System.out.println(bancoInscricao.buscarInscricaoPeloParticipante(sc.nextInt()));
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Id do Evento: ");
                    System.out.println(bancoInscricao.buscarInscricaoPeloEvento(sc.nextInt()));
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } catch (InscricaoNaoEncontradaException e) {
            System.out.println("Inscrição não encontrada");
        }
    }

    private static void criarInscricao() {
        System.out.print("Id do Participante: ");
        int idParticipante = sc.nextInt();
        System.out.print("Id do Evento: ");
        int idEvento = sc.nextInt();

        Inscricao inscricao = new Inscricao(bancoParticipante.buscarParticipantePorId(idParticipante), bancoEvento.buscarEventoPorId(idEvento));
        bancoInscricao.inscreverParticipante(inscricao);
    }


    //PARTICIPANTE
    private static void participanteMenu() {
        System.out.println("Menu Participante");
        System.out.print("Você deseja realizar qual ação?\n1 - Criar\n2 - Buscar\n3 - Deletar\n4 - Voltar\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                criarParticipante();
                break;
            case 2:
                buscarParticipante();
                break;
            case 3:
                deletarParticipante();
                break;
            case 4:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void deletarParticipante() {
        System.out.print("Este participante pode ter estar relacionado com inscrições em eventos, deseja excluir mesmo assim? (Inscrições seram deletadas!) " +
                "\n1 - Sim\n2 - Não\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                System.out.print("ID: ");
                bancoParticipante.removerParticipante(sc.nextInt());
                break;
            case 2:
                System.out.println("Operação cancelada");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void buscarParticipante() {
        System.out.print("1 - Buscar pelo Id\n2 - Buscar pelo Email\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        try {
            switch (escolha) {
                case 1:
                    System.out.print("Id: ");
                    System.out.println(bancoParticipante.buscarParticipantePorId(sc.nextInt()));
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Email: ");
                    System.out.println(bancoParticipante.buscarParticipantePorEmail(sc.nextLine()));
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } catch (ParticipanteNaoEncontradoException e) {
            System.out.println("Participante não encontrado");
        }
    }

    private static void criarParticipante() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Participante participante = new Participante(nome, email);
        bancoParticipante.adicionarParticipante(participante);
    }


    //EVENTO
    private static void eventoMenu() {
        System.out.println("Menu Evento");
        System.out.print("Você deseja realizar qual ação?\n1 - Criar\n2 - Buscar\n3 - Deletar\n4 - Voltar\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                criarEvento();
                break;
            case 2:

                buscarEvento();
                break;
            case 3:
                deletarEvento();
                break;
            case 4:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

    }

    private static void deletarEvento() {
        System.out.print("Este evento pode ter estar relacionado com inscrições de participantes, deseja excluir mesmo assim? (Inscrições seram deletadas!) " +
                "\n1 - Sim\n2 - Não\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                System.out.print("ID: ");
                bancoEvento.removerEvento(sc.nextInt());
                break;
            case 2:
                System.out.println("Operação cancelada");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void buscarEvento() {
        System.out.print("1 - Buscar pelo Id\n2 - Buscar pelo Nome\nR: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        try {
            switch (escolha) {
                case 1:
                    System.out.print("Id: ");
                    System.out.println(bancoEvento.buscarEventoPorId(sc.nextInt()));
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Nome: ");
                    System.out.println(bancoEvento.buscarEventoPorNome(sc.nextLine()));
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } catch (EventoNaoEncontradoException e) {
            System.out.println("Evento não encontrado");
        }
    }

    private static void criarEvento() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Local: ");
        String local = sc.nextLine();
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Descricão: ");
        String horario = sc.nextLine();

        Evento evento = new Evento(nome, local, data, horario);
        bancoEvento.adicionarEvento(evento);
    }
}
