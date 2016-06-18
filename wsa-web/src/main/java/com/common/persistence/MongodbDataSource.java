package com.common.persistence;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongodbDataSource {
    private String        host;
    private int           port;
    private String        source;
    private String        userName;
    private String        passWord;
    private MongoClient   mongoClient;
    private MongoDatabase mongoDatabase;

    public MongodbDataSource(String host, int port, String source, String userName, String passWord) {
        this.host = host;
        this.port = port;
        this.source = source;
        this.userName = userName;
        this.passWord = passWord;

        ServerAddress serverAddress = new ServerAddress(host, port);
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        seeds.add(serverAddress);

        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(userName,
            this.source, passWord.toCharArray());
        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        credentialList.add(mongoCredential);
        
//        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(com.mongodb.MongoClient.getDefaultCodecRegistry()).build();
//        this.mongoClient = new MongoClient(seeds, credentialList,options);
        this.mongoClient = new MongoClient(seeds, credentialList);
        this.mongoDatabase = mongoClient.getDatabase(this.source);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
    
    public void setMongoDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MongodbDataSource [host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", source=");
        builder.append(source);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", passWord=");
        builder.append(passWord);
        builder.append(", mongoClient=");
        builder.append(mongoClient);
        builder.append(", mongoDatabase=");
        builder.append(mongoDatabase);
        builder.append("]");
        return builder.toString();
    }


}
