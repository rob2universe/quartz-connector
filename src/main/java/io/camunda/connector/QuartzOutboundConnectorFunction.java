package io.camunda.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "QUARTZ_OUTBOUND_CONNECTOR",
    inputVariables = {"payload", "authentication"},
    type = "io.camunda:quartz:1")
public class QuartzOutboundConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuartzOutboundConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
    var connectorRequest = context.getVariablesAsType(QuartzOutboundConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private QuartzOutboundConnectorResult executeConnector(final QuartzOutboundConnectorRequest connectorRequest) {
    // TODO: implement connector logic
    LOGGER.info("Executing Quartz connector with request {}", connectorRequest);
    var result = new QuartzOutboundConnectorResult();

    result.setJobId("100" + connectorRequest.getMessage());
    return result;
  }
}
