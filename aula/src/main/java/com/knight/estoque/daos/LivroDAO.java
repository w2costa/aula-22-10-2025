package com.knight.estoque.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.knight.estoque.modelos.Livro;

public class LivroDAO {

    public static List<Livro> livros;

    static {
        livros = new ArrayList<>();

        livros.add(new Livro(2012,
                new ArrayList<>(Arrays.asList("Paulo Silveira", "Adriano Almeida")),
                "Casa do Código",
                "Guia do Programador",
                "Vá do \"nunca programei\" ..."));

        livros.add(new Livro(2012,
                new ArrayList<>(Arrays.asList("Vinícius Baggio Fuentes")),
                "Casa do Código",
                "Ruby on Rails",
                "Crie rapidamente aplicações web"));
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Livro> listarLivros(Integer numeroDaPagina, Integer tamanhoDaPagina) {
        int indiceInicial = numeroDaPagina * tamanhoDaPagina;
        int indiceFinal = indiceInicial + tamanhoDaPagina;

        indiceFinal = indiceFinal > livros.size() ? livros.size() : indiceFinal;
        indiceInicial = indiceInicial > indiceFinal ? indiceFinal : indiceInicial;

        return livros.subList(indiceInicial, indiceFinal);
    }

}
