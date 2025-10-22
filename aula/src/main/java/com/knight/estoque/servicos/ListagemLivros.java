package com.knight.estoque.servicos;

import java.util.ArrayList;
import java.util.List;

import com.knight.estoque.modelos.Livro;

import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

@WebService
public class ListagemLivros {

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro());
        return livros;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/livros", new ListagemLivros());
        System.out.println("Serviço inicializado!");
    }
}