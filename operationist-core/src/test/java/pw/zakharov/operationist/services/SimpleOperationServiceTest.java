package pw.zakharov.operationist.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pw.zakharov.operationist.Result;
import pw.zakharov.operationist.TestOperationHandler;
import pw.zakharov.operationist.registries.OperationRegistry;
import pw.zakharov.operationist.registries.SimpleOperationRegistry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleOperationServiceTest {

    static OperationRegistry<Result> operationRegistry;
    static OperationDispatcher<Result> operationDispatcher;
    static OperationService<Result> operationService;

    @BeforeAll
    static void setup() {
        operationRegistry = new SimpleOperationRegistry<>();
        operationDispatcher = new SimpleOperationDispatcher<>(operationRegistry);
        operationService = new SimpleOperationService<>(operationDispatcher);
    }

    @Test
    void executeSuccess() {
        var testOperationHandler = new TestOperationHandler();
        testOperationHandler.register("test", operationRegistry);

        var result = operationService.execute(
                List.of(
                        new TestOperationHandler.TestOperation("test", "DEV"),
                        new TestOperationHandler.TestOperation("test", "IFT")
                )
        );

        assertNotNull(result);
        assertTrue(result.success());
    }

    @Test
    void executeFailure() {
        var testOperationHandler = new TestOperationHandler();
        testOperationHandler.register("test", operationRegistry);

        assertThrows(
                RuntimeException.class,
                () -> operationService.execute(
                        List.of(
                                new TestOperationHandler.TestOperation("test", "DEV"),
                                new TestOperationHandler.TestOperation("test", "PROD")
                        )
                )
        );
    }
}