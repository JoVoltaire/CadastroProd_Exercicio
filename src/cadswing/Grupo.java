package cadswing;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private static final List<String> cor = new ArrayList<>(
            List.of("Azul", "Rosa", "Violeta","Azul Marinho", "Azul Ciano", "Salmão", "Verde Musgo", "Verde",
                    "Verde Escuro", "Yellow", "Laranja", "Laranja Forte", "Marrom", "Cinza"));
    
    private String nomeGrup;
    private boolean touchGrup;
    private String corGrup;
    
    public Grupo(String nomeGrup, boolean touchGrup) {
        this.nomeGrup = nomeGrup;
        this.touchGrup = touchGrup;
        this.corGrup = cor.get(0);
    }

    // Getters e setters
    public String getNomeGrup() {
        return nomeGrup;
    }

    public void setNomeGrup(String nomeGrup) {
        this.nomeGrup = nomeGrup;
    }

    public boolean isTouchGrup() {
        return touchGrup;
    }

    public void setTouchGrup(boolean touchGrup) {
        this.touchGrup = touchGrup;
    }

    public String getCorGrup() {
        return corGrup;
    }

    public void setCorGrup(String corGrup) {
        this.corGrup = corGrup;
    }
    
}
