package org.webapp.service;

import org.webapp.model.Produto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoServiceImp implements ProdutoService{
    @Override
    public List<Produto> listar() {
        return Arrays.asList(new Produto(1L, "notebook", "computador", 1700),
                new Produto(2L, "Mesa escritorio", "office", 1200),
                new Produto(3L, "teclado", "computador", 800));
    }

    @Override
    public Optional<Produto> getById(Long id) {
        return this.listar().stream().filter(p-> p.getId().equals(id)).findAny();
    }
}
