package io.millesabords.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

public class SecuredClient {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Usage : java " + SecuredClient.class.getName() + " <Elasticsearch hostname>");
            System.exit(1);
        }

        final String hostname = args[0];

        System.out.println("Hostname : " + hostname);

        final Client client = new TransportClient()
            .addTransportAddress(new InetSocketTransportAddress(hostname, 9500));

        final SearchResponse response = client.prepareSearch()
            .setSize(1)
            .setQuery(QueryBuilders.matchAllQuery())
            .execute().actionGet();

        System.out.println("response=" + response);
    }
}