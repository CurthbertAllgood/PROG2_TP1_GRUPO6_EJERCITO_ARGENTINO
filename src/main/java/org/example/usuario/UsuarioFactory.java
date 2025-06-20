package org.example.usuario;

public class UsuarioFactory{


    public static Usuario crearUsuario(USoldado soldado){
        Usuario usuario = null;

        switch (soldado.getGrado()){
            case SOLDADO->  usuario = new USoldado();
            case OFICIAL->  usuario = new UOficial();
            case SUBOFICIAL-> usuario = new USubOficial();

        }
        return usuario;
    }

}
