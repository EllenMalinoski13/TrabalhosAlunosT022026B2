package Fag;


	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	public class Principal {

	    static Scanner scan = new Scanner(System.in);
	    static List<Produto> listaProdutos = new ArrayList<Produto>();

	public static void main(String[] args) {
		  mostrarMenu();
    }

    public static void mostrarMenu() {
        int escolhaUsuario = 0;

        do {
            System.out.println("\n--- SISTEMA DE PRODUTOS ---");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Excluir produto");
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
                cadastrarProduto();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                buscarProduto();
                break;
            case 4:
                atualizarProduto();
                break;
            case 5:
                excluirProduto();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarProduto() {
        System.out.print("ID do produto: ");
        int id = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).getId() == id) {
                System.out.println("Esse ID já está cadastrado!");
                return;
            }
        }

        System.out.print("Nome do produto: ");
        String nome = scan.nextLine();

        System.out.print("Preço: ");
        double preco = scan.nextDouble();

        System.out.print("Quantidade: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        Produto produto = new Produto(id, nome, preco, quantidade);
        listaProdutos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void listarProdutos() {
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");

        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);

            System.out.println("\nID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade());
        }
    }

    public static void buscarProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Digite o ID do produto: ");
        int id = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);

            if (produto.getId() == id) {
                System.out.println("\nID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Preço: R$ " + produto.getPreco());
                System.out.println("Quantidade: " + produto.getQuantidade());

                encontrado = true;
            }
        }

        if (encontrado == false) {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void atualizarProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Digite o ID do produto: ");
        int id = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);

            if (produto.getId() == id) {
                System.out.print("Novo nome: ");
                produto.setNome(scan.nextLine());

                System.out.print("Novo preço: ");
                produto.setPreco(scan.nextDouble());

                System.out.print("Nova quantidade: ");
                produto.setQuantidade(scan.nextInt());
                scan.nextLine();

                System.out.println("Produto atualizado com sucesso!");
                encontrado = true;
            }
        }

        if (encontrado == false) {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void excluirProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Digite o ID do produto: ");
        int id = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);

            if (produto.getId() == id) {
                listaProdutos.remove(i);
                System.out.println("Produto excluído com sucesso!");
                encontrado = true;
                break;
            }
        }

        if (encontrado == false) {
            System.out.println("Produto não encontrado.");
        }
    }

	}

