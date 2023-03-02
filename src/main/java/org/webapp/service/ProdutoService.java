package org.webapp.service;

import org.webapp.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    List<Produto> listar();
    Optional<Produto> getById(Long id);

}
