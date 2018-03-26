package info.bitrich.xchangestream.cexio;

import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CexioManualExample {

    private static final Logger LOG = LoggerFactory.getLogger(CexioManualExample.class);

    public static void main(String[] args) throws IOException {
        CexioStreamingExchange exchange = (CexioStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(
                CexioStreamingExchange.class.getName());
        CexioProperties properties = new CexioProperties();
        exchange.setCredentials(properties.getApiKey(), properties.getSecretKey());
        exchange.connect().blockingAwait();

        exchange.getStreamingService().getOrder().subscribe(
                order -> LOG.info("Got order: {}", order.getId()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
