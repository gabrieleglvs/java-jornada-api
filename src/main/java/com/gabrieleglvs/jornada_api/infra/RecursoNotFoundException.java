package infra;

public class RecursoNotFoundException extends RuntimeException {
    public RecursoNotFoundException(String mensagem){
        super(mensagem);
    }
}
