package cz.muni.fi.PA165.flight.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: PC
 * Date: 14. 12. 2014
 * Time: 13:40
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(int id) {
		super("cannot find entity with id :" + id );
	}
}

