package app_be_mifac.generico;

import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Optional;


/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */
public interface GenericoService <T>{

    List<T> findByLikeObject(T t) throws ServiceException, app_be_mifac.exception.ServiceException;

    Optional<T> findById(Long id) throws ServiceException, app_be_mifac.exception.ServiceException;

    T save(T t) throws ServiceException, app_be_mifac.exception.ServiceException;

    T update(T t) throws ServiceException, app_be_mifac.exception.ServiceException;

    Boolean delete(T t) throws ServiceException, app_be_mifac.exception.ServiceException;

}
