package pw.zakharov.operationist.services;

import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

public interface OperationDispatcher<R extends Result> {

    <O extends OperationHandler.Operation> R dispatch(O operation);

}
