package model.entities.bo;


import java.util.List;

public interface GenericBO<T> {

    boolean criar(T o) throws Exception;
    boolean deletar(T o) throws Exception;
    boolean alterar(T o) throws Exception;
    List<T> listarTodos() throws Exception;
    T getById(long id) throws Exception;
    boolean validar(T o) throws Exception;
    boolean validaId(long id) throws Exception;

}