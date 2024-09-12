package app_be_mifac.service;

import app_be_mifac.entity.seguridad.Usuario;
import app_be_mifac.exception.ServiceException;
import app_be_mifac.repository.seguridad.UsuarioRepository;
import app_be_mifac.util.BDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	
	@Override
	public List<Usuario> findByLikeObject(Usuario t) throws ServiceException {
		try {
			return usuarioRepository.findByLikeEmail(BDUtil.getLike(t.getEmail()));
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Optional<Usuario> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario save(Usuario t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Usuario t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Usuario t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	




}
