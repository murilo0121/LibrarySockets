/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarycliente;

/**
 *
 * @author muriloerhardt
 */
public class ClienteInfo {
    String nome;
    String senha;
    //tipo: 
    //0 = adm
    //1 = user
    int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
