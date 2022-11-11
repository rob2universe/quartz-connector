package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class QuartzFunctionTest {

  @Test
  void shouldReturnReceivedMessageWhenExecute() throws Exception {
    // given
    var input = new QuartzOutboundConnectorRequest();
    var auth = new Authentication();
    input.setMessage("0815");
    input.setAuthentication(auth);
    auth.setToken("xobx-test");
    auth.setUser("testuser");
    var function = new QuartzOutboundConnectorFunction();
    var context = OutboundConnectorContextBuilder.create()
      .variables(input)
      .build();
    // when
    var result = function.execute(context);
    // then
    assertThat(result)
      .isInstanceOf(QuartzOutboundConnectorResult.class)
      .extracting("jobId")
      .isEqualTo("1000815");
  }
}