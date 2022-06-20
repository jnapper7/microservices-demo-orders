package works.weave.socks.orders.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.net.URI;

@ConfigurationProperties
@ConfigurationPropertiesScan
public class OrdersConfigurationProperties {
    private String domain = "";
    private String port = "80";

    public URI getPaymentUri() {
        return new ServiceUri(new Hostname("payment"), new Domain(domain), port, "/paymentAuth").toUri();
    }

    public URI getShippingUri() {
        return new ServiceUri(new Hostname("shipping"), new Domain(domain), "80", "/shipping").toUri();
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private class Hostname {
        private final String hostname;

        private Hostname(String hostname) {
            this.hostname = hostname;
        }

        @Override
        public String toString() {
            if (hostname != null && !hostname.equals("")) {
                return hostname;
            } else {
                return "";
            }
        }
    }

    private class Domain {
        private final String domain;

        private Domain(String domain) {
            this.domain = domain;
        }

        @Override
        public String toString() {
            if (domain != null && !domain.equals("")) {
                return "." + domain;
            } else {
                return "";
            }
        }
    }

    private class ServiceUri {
        private final Hostname hostname;
        private final Domain domain;
        private final String port;
        private final String endpoint;

        private ServiceUri(Hostname hostname, Domain domain, String port, String endpoint) {
            this.hostname = hostname;
            this.domain = domain;
            this.port = port;
            this.endpoint = endpoint;
        }

        public URI toUri() {
            if (port != null && !port.equals("")) {
                return URI.create(wrapHTTP(hostname.toString() + domain.toString() + ":" + port) + endpoint);
            } else {
                return URI.create(wrapHTTP(hostname.toString() + domain.toString()) + endpoint);
            }
        }

        private String wrapHTTP(String host) {
            return "http://" + host;
        }

        @Override
        public String toString() {
            return "ServiceUri{" +
                    "hostname=" + hostname + 
                    ", domain=" + domain +
                    '}';
        }
    }
}
