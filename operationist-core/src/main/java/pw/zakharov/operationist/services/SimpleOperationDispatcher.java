package pw.zakharov.operationist.services;

import lombok.RequiredArgsConstructor;
import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;
import pw.zakharov.operationist.exceptions.OperationExecutionException;
import pw.zakharov.operationist.exceptions.OperationValidationException;
import pw.zakharov.operationist.registries.OperationRegistry;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class SimpleOperationDispatcher<R extends Result> implements OperationDispatcher<R> {

    private final OperationRegistry<R> operationRegistry;

    @Override
    public <O extends OperationHandler.Operation> R dispatch(O operation) {
        requireNonNull(operation, "operation in null");

        var operationName = operation.name();
        requireNonNull(operationName, "operation name in null");

        var operationHandler = operationRegistry.getHandler(operationName);
        requireNonNull(operationHandler, String.format("Не найден обработчик для операции %s", operationName));

        try {
            return operationHandler.invoke(operation);
        } catch (OperationValidationException | OperationExecutionException e) {
            throw new RuntimeException(String.format("Ошибка во время выполнения операции %s", operationName), e);
        }
    }

}
