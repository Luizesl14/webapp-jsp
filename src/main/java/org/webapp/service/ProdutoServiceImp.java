package org.webapp.service;

import org.webapp.model.Produto;

import java.util.Arrays;
import java.util.List;

public class ProdutoServiceImp implements ProdutoService{
    @Override
    public List<Produto> listar() {
        return Arrays.asList(new Produto(1l, "notebook", "computador", 1700),
                new Produto(2l, "Mesa escritorio", "office", 1200),
                new Produto(3l, "teclado", "computador", 800));
    }
}
