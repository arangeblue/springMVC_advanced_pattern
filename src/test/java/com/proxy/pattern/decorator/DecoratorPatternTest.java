package com.proxy.pattern.decorator;

import com.proxy.pattern.decorator.code.DecoratorPatternClient;
import com.proxy.pattern.decorator.code.MessageDecorator;
import com.proxy.pattern.decorator.code.RealComponent;
import com.proxy.pattern.decorator.code.TimeDecorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternTest {
    
    @Test
    @DisplayName("데코레이터 패턴 테스트, 실 객체를 참조")
    public void noDecorator() {

        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);

        client.execute();

    }

    @Test
    @DisplayName("데코레이터 패턴 테스트, 데코레이터를 적용")
    public void decorator1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();

    }

    @Test
    @DisplayName("데코레이터 패턴 테스트, 데코레이터를 적용 + 다중 데코레이터 적용")
    public void decorator2() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
        
        //  client -> timeDecorator -> messageDecorator -> realComponent
    }

}
