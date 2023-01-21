package pw.zakharov.operationist;

import pw.zakharov.operationist.exceptions.OperationExecutionException;
import pw.zakharov.operationist.exceptions.OperationRegistrationException;
import pw.zakharov.operationist.exceptions.OperationValidationException;
import pw.zakharov.operationist.registries.OperationRegistry;

/**
 * Класс, который выполняет действие над объектом
 *
 * @param <R> Тип результата
 * @param <O> Тип операции
 */
public interface OperationHandler<R extends Result, O extends OperationHandler.Operation> {

    /**
     * Регистрация исполнителя операции
     *
     * @param name              Название операции
     * @param operationRegistry Регистр в который нужно зарегистрировать операцию
     * @throws OperationRegistrationException Если возникла ошибка во время регистрации
     */
    void register(String name, OperationRegistry<R> operationRegistry) throws OperationRegistrationException;

    /**
     * Вызвать выполнение операции
     *
     * @param operation Информация об операции, которую выполняем
     * @return Результат выполнения операции
     * @throws OperationValidationException Если возникла ошибка валидации
     * @throws OperationExecutionException  Если возникла ошибка исполнения
     */
    R invoke(O operation) throws OperationValidationException, OperationExecutionException;

    interface Operation {

        String name();

        int weight();

        boolean parallel();
    }

}