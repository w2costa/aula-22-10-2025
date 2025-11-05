package com.knight.estoque.servicos;

import java.util.List;

import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPException;
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

    public void criarLivro(@WebParam(name = "livro") Livro livro,
            @WebParam(name = "usuario", header = true) Usuario usuario)
            throws UsuarioNaoAutorizadoException, SOAPException {
        if (usuario.getLogin().equals("soa") &&
                usuario.getSenha().equals("soa")) {
            obterDAO().criarLivro(livro);
        } else {
            throw new UsuarioNaoAutorizadoException("Não autorizado");
        }
    }

    private LivroDAO obterDAO() {
        return new LivroDAO();
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/livros", new ListagemLivros());
        System.out.println("Serviço inicializado!");
    }
}