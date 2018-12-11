package com.example.pedro.bolsas;

public class LivroModel {
    private String titulo;
    private String editora;
    private String autor;
    public LivroModel(String titulo, String autor, String editora){
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
