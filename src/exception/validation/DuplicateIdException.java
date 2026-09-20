package exception.validation;

import exception.base.IceCreamException;

public class DuplicateIdException extends IceCreamException {
    public DuplicateIdException(String massage) {
        super(massage);
    }
}
