package pw.zakharov.operationist;

import lombok.RequiredArgsConstructor;
import pw.zakharov.operationist.registries.OperationRegistry;

public abstract class BaseOperationHandler<O extends OperationHandler.Operation>
        implements OperationHandler<Result, O> {

    @Override
    public void register(String name, OperationRegistry<Result> operationRegistry) {
        operationRegistry.addHandler(name, this);
    }

    @RequiredArgsConstructor
    public abstract static class BaseOperation implements Operation {

        private final String name;

        @Override
        public String name() {
            return name;
        }

        @Override
        public int weight() {
            return 0;
        }

        @Override
        public boolean parallel() {
            return false;
        }

    }

}
