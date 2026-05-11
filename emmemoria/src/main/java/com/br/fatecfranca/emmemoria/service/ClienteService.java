package com.br.fatecfranca.emmemoria.service;

import com.br.fatecfranca.emmemoria.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final List<Cliente> lista = new ArrayList<>();
    private Long nextId = 1L;

    public List<Cliente> listar() {
        return lista;
    }

    public Cliente criar(Cliente cliente) {
        cliente.setId(nextId);
        nextId++;
        lista.add(cliente);
        return cliente;
    }

    public Cliente buscarPorId(Long id) {
        return lista.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean remover(Long id) {
        return lista.removeIf(cliente -> cliente.getId().equals(id));
    }

    public Cliente atualizar(Long id, Cliente novo) {
        Cliente existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        int index = lista.indexOf(existente);
        novo.setId(id);
        lista.set(index, novo);
        return novo;
    }
}
