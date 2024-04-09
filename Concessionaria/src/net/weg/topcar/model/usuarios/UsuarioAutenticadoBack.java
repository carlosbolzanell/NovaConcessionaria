package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.usuarios.Cliente;

public class UsuarioAutenticadoBack {
    private static Cliente usuarioAutenticado;

    public static void setUsuarioAutenticado(Cliente usuarioAutenticado) {
        usuarioAutenticado = usuarioAutenticado;
    }

    public static Cliente getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
