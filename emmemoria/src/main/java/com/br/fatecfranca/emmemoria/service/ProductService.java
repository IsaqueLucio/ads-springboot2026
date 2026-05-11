package com.br.fatecfranca.emmemoria.service;

import com.br.fatecfranca.emmemoria.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    // cria a lista de produto
    List<Product> lista = new ArrayList<Product>();
    private Long nextInt = 1L;

    public List<Product> list() {
        return lista;
    }

    public Product create(Product novo) {
        novo.setId(nextInt);
        nextInt++;
        lista.add(novo);
        return novo;
    }

    public boolean remove(Long id) {
        return lista.removeIf(p -> p.getId().equals(id));
    }

    public Product update(Long id, Product novo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                novo.setId(id);
                lista.set(i, novo);
                return novo;
            }
        }
        return null;
    }
}
