package pw.zakharov.operationist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pw.zakharov.operationist.exceptions.OperationExecutionException;
import pw.zakharov.operationist.registries.OperationRegistry;

@Component
public class TestOperationHandler extends BaseOperationHandler<TestOperationHandler.TestOperation> {

    @Autowired
    public void register(OperationRegistry<Result> operationRegistry) {
        register("test", operationRegistry);
    }

    @Override
    public Result invoke(TestOperation operation) throws OperationExecutionException {
        return switch (operation.getStandName()) {
            case "DEV", "IFT" -> BaseResult.ok();
            default -> throw new OperationExecutionException("Передано неизвестное название тестового стенда");
        };
    }

    public static class TestOperation extends BaseOperation {

        @Getter
        private final String standName;

        public TestOperation(String name, String standName) {
            super(name);

            this.standName = standName;
        }

    }

}
