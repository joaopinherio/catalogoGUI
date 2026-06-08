package br.pucrs.nomeusuario.exemplo.dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private int ID;
    private String nome;
    private String email;
    private String pais;
    private LocalDate dataNascimento;
    private String formattedDataNascimento;

    private static int proximoID = 1;

    // Construtor atualizado
    public Pessoa(String nome, String email, String pais, LocalDate dataNascimento) {
        this.ID = proximoID++;
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.dataNascimento = dataNascimento;
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDataNascimento = dataNascimento.format(formatador);
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public String getFormattedDataNascimento() {
        return formattedDataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDataNascimento = dataNascimento.format(formatador);
    }

}