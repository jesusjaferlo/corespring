package br.com.itexto.springforum.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.itexto.springforum.dao.DAOPermisoUsuario;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.PermisoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

/**
 * Exemplo de authentication provider
 * @author kicolobo
 */
public class SFAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private DAOUsuario daoUsuario;
	
	public DAOUsuario getDaoUsuario() {return daoUsuario;}
	public void setDaoUsuario(DAOUsuario dao) {daoUsuario = dao;}
	
	@Autowired
	private DAOPermisoUsuario daoPermissao;
	
	public DAOPermisoUsuario getDaoPermissao() {return daoPermissao;}
	public void setDaoPermissao(DAOPermisoUsuario dao) {daoPermissao = dao;}
	
	public Authentication authenticate(Authentication auth)	throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		String username = token.getName();
		String senha    = token.getCredentials() != null ? token.getCredentials().toString() : null;
		Usuario usuario = getDaoUsuario().getUsuario(username, senha);
		if (usuario == null) {
			return null;
		}
		List<PermisoUsuario> permissoes = getDaoPermissao().getPermisoUsuario(usuario);
		SFAuthentication resultado = new SFAuthentication(usuario, permissoes);
		resultado.setAuthenticated(usuario != null);
		return resultado;
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
