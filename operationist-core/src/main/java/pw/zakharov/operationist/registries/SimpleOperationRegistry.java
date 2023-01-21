package pw.zakharov.operationist.registries;

import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleOperationRegistry<R extends Result> implements OperationRegistry<R> {

    private final Map<String, OperationHandler<R, ? extends OperationHandler.Operation>> registry;

    public SimpleOperationRegistry() {
        this.registry = new ConcurrentHashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <O extends OperationHandler.Operation> OperationHandler<R, O> getHandler(String name) {
        return (OperationHandler<R, O>) registry.get(name);
    }

    @Override
    public boolean addHandler(String name, OperationHandler<R, ?> operationHandler) {
        return registry.put(name, operationHandler) == null;
    }

}
