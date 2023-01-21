package pw.zakharov.operationist.registries;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import pw.zakharov.operationist.OperationHandler;
import pw.zakharov.operationist.Result;

import static org.springframework.core.ResolvableType.forInstance;

@RequiredArgsConstructor
public class SpringDelegateOperationRegistry<R extends Result> implements OperationRegistry<R> {

    private final BeanFactory beanFactory;
    private final OperationRegistry<R> delegateOperationRegistry;

    @Override
    public <O extends OperationHandler.Operation> OperationHandler<R, O> getHandler(String name) {
        var fromDelegateRegistry = delegateOperationRegistry.<O>getHandler(name);
        var operationHandlerType = forInstance(fromDelegateRegistry);
        var beanProvider = beanFactory.<OperationHandler<R, O>>getBeanProvider(operationHandlerType);
        return beanProvider.getIfAvailable();
    }

    @Override
    public boolean addHandler(String name, OperationHandler<R, ?> operationHandler) {
        return delegateOperationRegistry.addHandler(name, operationHandler);
    }

}
