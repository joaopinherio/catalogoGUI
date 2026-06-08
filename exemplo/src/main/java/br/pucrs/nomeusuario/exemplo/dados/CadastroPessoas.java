package br.pucrs.nomeusuario.exemplo.dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoas {
    private static CadastroPessoas instance;

    public static CadastroPessoas getInstance() {
        if (instance == null)
            instance = new CadastroPessoas();
        return instance;
    }

    private List<Pessoa> lista;

    private CadastroPessoas() {
        lista = new ArrayList<>();

        lista.add(new Pessoa("Luke Skywalker", "luke@email.com", "Brasil", LocalDate.of(1977, 05, 25)));
        lista.add(new Pessoa("Leia Organa", "leia@email.com", "Portugal", LocalDate.of(1977, 05, 25)));
        lista.add(new Pessoa("Han Solo", "solo@email.com", "Brasil", LocalDate.of(1932, 05, 24)));
        lista.add(new Pessoa("Darth Vader", "vader@email.com", "EUA", LocalDate.of(1941, 01, 3)));
        lista.add(new Pessoa("Yoda", "yoda@email.com", "EUA", LocalDate.of(1845, 01, 7)));
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public boolean cadastrar(String nome, String email,
            String pais, LocalDate dn) {
        return lista.add(new Pessoa(nome, email, pais, dn));
    }

    public boolean cadastrar(Pessoa p) {
        return lista.add(p);
    }

    public void update(int id, Pessoa upd) {
        Pessoa p = lista.stream()
                .filter(a -> a.getID() == id)
                .findFirst()
                .orElse(null);
        if (p != null) {
            p.setDataNascimento(upd.getDataNascimento());
            p.setEmail(upd.getEmail());
            p.setPais(upd.getPais());
        }
    }
}
