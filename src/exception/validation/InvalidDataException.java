package exception.validation;

import exception.base.IceCreamException;

public class InvalidDataException extends IceCreamException{
    public InvalidDataException(String massage) {
        super(massage);
    }
}
