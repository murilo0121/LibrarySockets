/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

/**
 *
 * @author murilo.erhardt
 */
public class User {
    
    private String code;
    private String nome;
    private String senha;
    private boolean type; // tipo de usuário, user=true ou admin=false

    public User(String code, String nome, String senha, boolean type) {
        this.code = code;
        this.nome = nome;
        this.senha = senha;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
    
    
}
