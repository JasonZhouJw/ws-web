package com.common.persistence;

import static org.junit.Assert.assertNotNull;

import org.bson.Document;
import org.bson.types.Binary;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.persistence.dataobject.Person;
import com.common.utils.SerializeUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongodbDataSourceTest {
    private static MongodbDataSource dataSource  = null;
    private static String            host        = "192.168.1.234";
    private static int               port        = 27017;
    private static String            userName    = "testdata";
    private static String            passWord    = "123456";
    private static String            source      = "test";

    private static final String      COLLECTION  = "MongodbDataSourceTest";
    private static final String      DATA_OBJECT = "DataObject";
    private static final String      SAMPLE_ID   = "SampleId";

    @BeforeClass
    public static void init() {
        dataSource = new MongodbDataSource(host, port, source, userName, passWord);
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMongodbDataSource() {
        System.out.println("begin to testMongodbDataSource========================");
        assertNotNull(dataSource);
        MongoDatabase db = dataSource.getMongoDatabase();
        MongoCollection<Document> persons = db.getCollection("person");
        FindIterable<Document> find = persons.find();
        MongoCursor<Document> cursor = find.iterator();
        while (cursor.hasNext()) {
            Document dc = cursor.next();
            System.out.println(dc);
            String name = dc.getString("name");
            System.out.println("name = " + name);
            Double age = dc.getDouble("age");
            System.out.println("age = " + age);
        }

        System.out.println("-----------------------find-----------------------");
        FindIterable<Document> fi = persons.find(Filters.eq("name", "jack"));
        System.out.println(fi.first());

        System.out.println("-----------------------insert-----------------------");
        Document doc = new Document("name", "allen");
        doc.append("age", 14);
        persons.insertOne(doc);

        System.out.println("-----------------------remove-----------------------");
        persons.deleteOne(Filters.lt("age", 15));
        System.out.println("end to testMongodbDataSource========================");
    }

    @Test
    public void testMongodbDataSource_Serialize_insert() {
        System.out.println("begin to testMongodbDataSource_Serialize========================");
        MongoDatabase db = dataSource.getMongoDatabase();
        MongoCollection<Document> col = db.getCollection(COLLECTION);

        Person p = new Person("allen", 14);
        SerializeUtil<Person> sp = new SerializeUtil<Person>();

        Document dc = new Document(SAMPLE_ID, "1").append(DATA_OBJECT, sp.toByteArray(p));
        col.insertOne(dc);
        System.out.println("--------------------------insert allen---------------------");

        p = new Person("bob", 15);

        dc = new Document(SAMPLE_ID, "2").append(DATA_OBJECT, sp.toByteArray(p));
        col.insertOne(dc);
        System.out.println("--------------------------insert bob---------------------");
        System.out.println("end to testMongodbDataSource_Serialize========================");
    }

    @Test
    public void testMongodbDataSource_Serialize_selectlist() {
        System.out.println("begin to testMongodbDataSource_Serialize2========================");
        MongoDatabase db = dataSource.getMongoDatabase();
        MongoCollection<Document> col = db.getCollection(COLLECTION);

        SerializeUtil<Person> sp = new SerializeUtil<Person>();

        FindIterable<Document> fi = col.find();
        MongoCursor<Document> cursor = fi.iterator();
        Person p = null;
        while (cursor.hasNext()) {
            Document dc = cursor.next();
            Binary obj = (Binary) dc.get(DATA_OBJECT);
            p = sp.assembleObject(obj);
            System.out.println("Person: " + p);
        }
        System.out.println("end to testMongodbDataSource_Serialize2========================");
    }
    
    @Test
    public void testMongodbDataSource_Serialize_queryone(){
        System.out.println("begin to testMongodbDataSource_Serialize_query========================");
        MongoDatabase db = dataSource.getMongoDatabase();
        MongoCollection<Document> col = db.getCollection(COLLECTION);
        
        SerializeUtil<Person> sp = new SerializeUtil<Person>();
        Person p = null;
        
        FindIterable<Document> fi = col.find(Filters.eq(SAMPLE_ID, "2"));
        MongoCursor<Document> cursor = fi.iterator();
        while (cursor.hasNext()) {
            Document dc = cursor.next();
            Binary obj = (Binary) dc.get(DATA_OBJECT);
            p = sp.assembleObject(obj);
            System.out.println("Person: " + p);
        }
        System.out.println("end to testMongodbDataSource_Serialize_query========================");
    }
    
    @AfterClass
    public static void afterClass(){
        //用于单元测试后清空数据
//        mongodbDataSource_Serialize_removeall();
    }
    
    public static void mongodbDataSource_Serialize_removeall(){
        MongoDatabase db = dataSource.getMongoDatabase();
        MongoCollection<Document> col = db.getCollection(COLLECTION);
        col.deleteMany(Filters.or(Filters.eq(SAMPLE_ID, "1"), Filters.eq(SAMPLE_ID, "2")));
    }
}
