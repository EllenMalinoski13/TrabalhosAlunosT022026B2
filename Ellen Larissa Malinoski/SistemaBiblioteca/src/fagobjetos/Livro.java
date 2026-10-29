package fagobjetos;

import java.util.Date;

public class Livro {

    private String nome;
    private String autor;
    private Date dataemprestimo;

    public Livro() {
    }

    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataemprestimo() {
        return dataemprestimo;
    }

    public void setDataemprestimo(Date dataemprestimo) {
        this.dataemprestimo = dataemprestimo;
    }

    public String validarDisponibilidade() {
        if (dataemprestimo == null) {
            return "disponivel";
        } else {
            return "indisponivel";
        }
    }
}