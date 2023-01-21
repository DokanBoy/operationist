package pw.zakharov.operationist.registries;

import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

public interface OperationRegistry<R extends Result> {

    <O extends OperationHandler.Operation> OperationHandler<R, O> getHandler(String name);

    boolean addHandler(String name, OperationHandler<R, ?> operationHandler);

}
