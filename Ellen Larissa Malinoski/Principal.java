
package fag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fagobjetos.Livro;
import fagobjetos.Pessoa;

public class Principal {

    static Scanner scan = new Scanner(System.in);
    static List<Livro> listaLivros = new ArrayList<Livro>();
    static List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int escolhaUsuario = 0;

        do {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1 - Listar livros");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Excluir livro");
            System.out.println("4 - Emprestar livro");
            System.out.println("5 - Devolver livro");
            System.out.println("6 - Cadastrar usuário");
            System.out.println("7 - Listar usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            escolhaUsuario = scan.nextInt();
            scan.nextLine();

            validarEscolha(escolhaUsuario);

        } while (escolhaUsuario != 0);

        System.out.println("Sistema encerrado!");
    }

    public static void validarEscolha(int escolhaUsuario) {
        switch (escolhaUsuario) {
            case 0:
                break;
            case 1:
                listarLivros();
                break;
            case 2:
                cadastrarLivro();
                break;
            case 3:
                excluirLivro();
                break;
            case 4:
                emprestarLivro();
                break;
            case 5:
                devolverLivro();
                break;
            case 6:
                cadastrarPessoa();
                break;
            case 7:
                listarPessoas();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarLivro() {
        System.out.print("Nome do livro: ");
        String nome = scan.nextLine();

        System.out.print("Autor do livro: ");
        String autor = scan.nextLine();

        Livro livro = new Livro(nome, autor);
        listaLivros.add(livro);

        System.out.println("Livro cadastrado com sucesso!");
        System.out.println("Total de livros: " + listaLivros.size());
    }

    public static void listarLivros() {
        System.out.println("\n--- LIVROS CADASTRADOS ---");

        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (int i = 0; i < listaLivros.size(); i++) {
            Livro livro = listaLivros.get(i);

            System.out.println((i + 1) + " - "
                    + livro.getNome() + " - "
                    + livro.getAutor() + " - "
                    + livro.validarDisponibilidade());
        }
    }

    public static void excluirLivro() {
        if (listaLivros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        listarLivros();

        System.out.print("Número do livro para excluir: ");
        int indice = scan.nextInt() - 1;
        scan.nextLine();

        if (indice < 0 || indice >= listaLivros.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Livro livro = listaLivros.get(indice);

        if (livro.getDataemprestimo() != null) {
            System.out.println("Não é possível excluir um livro emprestado.");
            return;
        }

        listaLivros.remove(indice);
        System.out.println("Livro excluído com sucesso!");
    }

    public static void cadastrarPessoa() {
        System.out.print("Nome do usuário: ");
        String nome = scan.nextLine();

        Pessoa pessoa = new Pessoa(nome);
        listaPessoas.add(pessoa);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void listarPessoas() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");

        if (listaPessoas.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (int i = 0; i < listaPessoas.size(); i++) {
            System.out.println((i + 1) + " - "
                    + listaPessoas.get(i).getNome());
        }
    }

    public static void emprestarLivro() {
        if (listaPessoas.isEmpty()) {
            System.out.println("Cadastre um usuário primeiro.");
            return;
        }

        if (listaLivros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        listarPessoas();

        System.out.print("Número do usuário: ");
        int indicePessoa = scan.nextInt() - 1;
        scan.nextLine();

        if (indicePessoa < 0 || indicePessoa >= listaPessoas.size()) {
            System.out.println("Usuário inválido.");
            return;
        }

        Pessoa pessoa = listaPessoas.get(indicePessoa);

        if (pessoa.getLivrosEmprestados().size() >= 2) {
            System.out.println("O usuário já possui 2 livros emprestados.");
            return;
        }

        listarLivros();

        System.out.print("Número do livro para emprestar: ");
        int indiceLivro = scan.nextInt() - 1;
        scan.nextLine();

        if (indiceLivro < 0 || indiceLivro >= listaLivros.size()) {
            System.out.println("Livro inválido.");
            return;
        }

        Livro livro = listaLivros.get(indiceLivro);

        if (livro.getDataemprestimo() != null) {
            System.out.println("Este livro já está emprestado.");
            return;
        }

        livro.setDataemprestimo(new Date());
        pessoa.getLivrosEmprestados().add(livro);

        System.out.println("Livro emprestado com sucesso!");
    }

    public static void devolverLivro() {
        if (listaPessoas.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
            return;
        }

        listarPessoas();

        System.out.print("Número do usuário: ");
        int indicePessoa = scan.nextInt() - 1;
        scan.nextLine();

        if (indicePessoa < 0 || indicePessoa >= listaPessoas.size()) {
            System.out.println("Usuário inválido.");
            return;
        }

        Pessoa pessoa = listaPessoas.get(indicePessoa);

        if (pessoa.getLivrosEmprestados().isEmpty()) {
            System.out.println("Este usuário não possui livros emprestados.");
            return;
        }

        System.out.println("\n--- LIVROS EMPRESTADOS ---");

        for (int i = 0; i < pessoa.getLivrosEmprestados().size(); i++) {
            System.out.println((i + 1) + " - "
                    + pessoa.getLivrosEmprestados().get(i).getNome());
        }

        System.out.print("Número do livro para devolver: ");
        int indiceLivro = scan.nextInt() - 1;
        scan.nextLine();

        if (indiceLivro < 0
                || indiceLivro >= pessoa.getLivrosEmprestados().size()) {
            System.out.println("Número inválido.");
            return;
        }

        Livro livro = pessoa.getLivrosEmprestados().get(indiceLivro);

        livro.setDataemprestimo(null);
        pessoa.getLivrosEmprestados().remove(indiceLivro);

        System.out.println("Livro devolvido com sucesso!");
    }
}