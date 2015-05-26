package com.epam.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Date;

/**
 * @author Dzmitry Misiuk
 */
public class TestLocalSolr {

    public static void main(String[] args) throws IOException, SolrServerException {
        Date start = new Date();
        String urlString = "http://localhost:8983/solr/gettingstarted";
        SolrClient solr = new HttpSolrClient(urlString);

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "3");
        doc.addField("title", "misha");

        UpdateResponse updateResponse = solr.add(doc);
        solr.commit();
        SolrPingResponse ping = solr.ping();
        System.out.println(ping);
        System.out.println(updateResponse);

        solr.deleteById("3");

        solr.commit();


        SolrQuery solrQuery = new SolrQuery("*:*");

        QueryResponse queryResponse = solr.query(solrQuery);
        queryResponse.getResults().forEach(System.out::println);

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());

    }
}
