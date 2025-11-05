package com.knight.estoque.servicos;

import java.util.List;

import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;

import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

@WebService
public class ListagemLivros {

    @WebResult(name = "livro")
    public List<Livro> listarLivros() {
        LivroDAO livroDAO = obterDAO();
        return livroDAO.listarLivros();
    }

   @WebResult(name = "livro")
    public List<Livro> listarLivrosPaginacao(Integer numeroDaPagina, Integer tamanhoDaPagina) {
        LivroDAO livroDAO = obterDAO();
        return livroDAO.listarLivros(numeroDaPagina, tamanhoDaPagina);
    }

    private LivroDAO obterDAO() {
        return new LivroDAO();
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/livros", new ListagemLivros());
        System.out.println("Serviço inicializado!");
    }
}