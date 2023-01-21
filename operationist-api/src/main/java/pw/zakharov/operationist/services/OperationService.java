package pw.zakharov.operationist.services;

import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

import java.util.List;

public interface OperationService<R extends Result> {

    R execute(List<? extends OperationHandler.Operation> operations);

}
