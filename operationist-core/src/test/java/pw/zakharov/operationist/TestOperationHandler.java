package pw.zakharov.operationist;

import lombok.Getter;
import pw.zakharov.operationist.exceptions.OperationExecutionException;

public class TestOperationHandler extends BaseOperationHandler<TestOperationHandler.TestOperation> {

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
