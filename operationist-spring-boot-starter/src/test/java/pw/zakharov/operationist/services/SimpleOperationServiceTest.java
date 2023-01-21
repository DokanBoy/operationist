package pw.zakharov.operationist.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pw.zakharov.operationist.Result;
import pw.zakharov.operationist.TestOperationHandler;
import pw.zakharov.operationist.configurations.OperationistAutoConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        classes = {
                OperationistAutoConfiguration.class,
                TestOperationHandler.class
        }
)
class SimpleOperationServiceTest {

    @Autowired
    OperationService<Result> operationService;

    @Test
    void executeSuccess() {
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