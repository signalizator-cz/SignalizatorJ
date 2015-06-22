package org.praguehacks.signalizatorJ.analyzer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.praguehacks.signalizatorJ.database.Address;
import org.praguehacks.signalizatorJ.database.AddressManager;
import org.praguehacks.signalizatorJ.database.Document;
import org.praguehacks.signalizatorJ.database.DocumentManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import elasticsearch;

/**
 * Created by kucerajn on 13.6.2015.
 */
public class analyzer {

    @Autowired
    DocumentManager documentManager;
    @Autowired
    AddressManager addressManager;
    
    StandardAnalyzer analyzer = new StandardAnalyzer();
    IndexWriterConfig config = new IndexWriterConfig(analyzer);
    
    public void batchAnalyze() throws IOException, ParseException {
        List<Document> documents = documentManager.getAll();
        
        List<String> tokens = new ArrayList<String>();
        
        //DataSource dataSource = new DataSource(); 
        
        for(Document docEnt : documents) {
            Directory docDir = createIndexFromDocument(docEnt);
            List<Address> addresses = getTokensFromDocument(docDir, tokens); 
            if(addresses != null) {
                for (Address add : addresses) {
                    getGeometry(add);
                }
                addressManager.saveAll(addresses);
            }
        }
    }
    
    private void getGeometry(Address add) {
        
    }
    
    private static void addDoc(IndexWriter w, String text, String id) throws IOException {
        org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
        doc.add(new TextField("text", text, Field.Store.YES));
        doc.add(new StringField("id", id, Field.Store.YES));
        w.addDocument(doc);
    }
    
    public Directory createIndexFromDocument(Document docEnt) throws IOException {
//        if (documents == null) {
//            documents = dataManager.getAll();    
//        }
        Directory index = new RAMDirectory();
        IndexWriter w = new IndexWriter(index, config);
        addDoc(w, docEnt.getText(), Integer.toString(docEnt.getId()));
        
        return index;
    }
    
    
    public  List<Address> getTokensFromDocument(Directory directory, List<String> tokens) throws ParseException, IOException {

        List<Address> addresses = new ArrayList<Address>();
        
        for (String token : tokens) {
            String querystr = token;
            Query q = new QueryParser("text", analyzer).parse(querystr);

            int maxHitsPerDoc = 1;

//            IndexWriter writer = new IndexWriter(directory, config);
            
            IndexReader reader = DirectoryReader.open(directory);
            
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(maxHitsPerDoc);
            searcher.search(q, collector);
            
            if (collector.getTotalHits() > 0) {
                Address address = new Address();
                address.setAddress(token);
                address.setDocumentId(collector.topDocs().scoreDocs[0].doc);
                addresses.add(address);
            }
            
        }
        return addresses;
    }
    
    
}
