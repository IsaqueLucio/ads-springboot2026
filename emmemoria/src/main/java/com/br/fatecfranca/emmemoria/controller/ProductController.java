package com.br.fatecfranca.emmemoria.controller;

import com.br.fatecfranca.emmemoria.model.Product;
import com.br.fatecfranca.emmemoria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> listResponseEntity() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<Product> cria(@RequestBody Product product) {
        Product criado = service.create(product);
        URI uri = URI.create("/produtos/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return service.remove(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizar(@PathVariable Long id,
                                             @RequestBody Product novo) {
        Product prod = service.update(id, novo);
        return (prod != null) ? ResponseEntity.ok(prod) : ResponseEntity.notFound().build();
    }
}
