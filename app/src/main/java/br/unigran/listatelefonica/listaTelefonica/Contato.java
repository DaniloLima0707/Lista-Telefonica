package br.unigran.listatelefonica.listaTelefonica;

public class Contato {
    private String campoNom;
    private String campoTel;
    private String campoDataNasc;
    private Integer id;

    public String getCampoNom() {return campoNom; }

    public void setCampoNom(String campoNom) { this.campoNom = campoNom; }

    public String getCampoTel() {return campoTel; }

    public void setCampoTel(String campoTel) { this.campoTel = campoTel; }

    public String getCampoDataNasc() {return campoDataNasc; }

    public void setCampoDataNasc(String campoDataNasc) { this.campoDataNasc = campoDataNasc; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Override
    public String toString() {
        return
                "Nome - " + campoNom +
                        " | Tel - " + campoTel +
                        " | Nasc - " + campoDataNasc;
    }
}
