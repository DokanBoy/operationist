package pw.zakharov.operationist.services;

import lombok.RequiredArgsConstructor;
import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class SimpleOperationService<R extends Result> implements OperationService<R> {

    private final OperationDispatcher<R> operationDispatcher;

    @Override
    public R execute(List<? extends OperationHandler.Operation> operations) {
        requireNonNull(operations, "operations in null");

        R globalResult = null;
        for (var operation : operations) {
            globalResult = operationDispatcher.dispatch(operation); // todo: собрать общий Result по всем операциям
        }
        return globalResult;
    }

}
