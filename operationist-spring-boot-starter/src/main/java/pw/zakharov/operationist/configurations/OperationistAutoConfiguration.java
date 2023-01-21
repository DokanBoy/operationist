package pw.zakharov.operationist.configurations;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pw.zakharov.operationist.Result;
import pw.zakharov.operationist.registries.OperationRegistry;
import pw.zakharov.operationist.registries.SimpleOperationRegistry;
import pw.zakharov.operationist.registries.SpringDelegateOperationRegistry;
import pw.zakharov.operationist.services.OperationDispatcher;
import pw.zakharov.operationist.services.OperationService;
import pw.zakharov.operationist.services.SimpleOperationDispatcher;
import pw.zakharov.operationist.services.SimpleOperationService;

@Configuration
public class OperationistAutoConfiguration {

    @Bean
    public OperationRegistry<Result> operationRegistry(BeanFactory beanFactory) {
        return new SpringDelegateOperationRegistry<>(beanFactory, new SimpleOperationRegistry<>());
    }

    @Bean
    public OperationDispatcher<Result> operationDispatcher(OperationRegistry<Result> operationRegistry) {
        return new SimpleOperationDispatcher<>(operationRegistry);
    }

    @Bean
    public OperationService<Result> operationService(OperationDispatcher<Result> operationDispatcher) {
        return new SimpleOperationService<>(operationDispatcher);
    }

}
