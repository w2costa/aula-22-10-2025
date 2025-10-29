package com.knight.estoque.servicos;

import java.util.ArrayList;
import java.util.List;

import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService
public class ListagemLivros {

    @WebResult(name = "livro")
    public List<Livro> listarLivros() {
        LivroDAO livroDAO = obterDAO();
        return livroDAO.listarLivros();
    }

    @RequestWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacao", localName = "listarLivrosPaginacao")
    @ResponseWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacaoResponse", localName = "listarLivrosPaginacaoResponse")
    @WebMethod(operationName = "listarLivrosPaginacao")
    @WebResult(name = "livro")
    public List<Livro> listarLivros(Integer numeroDaPagina,
            Integer tamanhoDaPagina) {
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