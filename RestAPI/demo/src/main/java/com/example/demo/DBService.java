package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DBService {

    

    public DBModel getCardDetails(final String name) throws InterruptedException, ExecutionException {
        final Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
        System.out.println(dbFirestore);
       final CollectionReference documentReference = dbFirestore.collection("cards");
        System.out.println(documentReference);
        DBModel dbModel = null;


        final Query museums = dbFirestore.collectionGroup("cards");
    final ApiFuture<QuerySnapshot> querySnapshot = museums.get();
    for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
    System.out.println(document.getId());
    dbModel=document.toObject(DBModel.class);
                    }
  
            return dbModel;

    
 }

 public String randomStringGen() throws InterruptedException, ExecutionException {

    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
 
    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
 
    System.out.println(generatedString);
    return generatedString;
    

}

 public String setCardDetails(Map docData) throws InterruptedException, ExecutionException {
    final Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    String generatedId=this.randomStringGen();

    ApiFuture<WriteResult> future = db.collection("cards").document(generatedId).set(docData);

    System.out.println("Update time : " + future.get().getUpdateTime());
    

        return "Success";


}

public String updateDetails(String id,Map docData) throws InterruptedException, ExecutionException {
    final Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    //String generatedId=this.randomStringGen();

    ApiFuture<WriteResult> future = db.collection("cards").document(id).update(docData);

    System.out.println("Update time : " + future.get().getUpdateTime());
    

        return "Success";


 }

 public String deleteDetails(String id) throws InterruptedException, ExecutionException {
    final Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    //String generatedId=this.randomStringGen();

    ApiFuture<WriteResult> future = db.collection("cards").document(id).delete();

    System.out.println("delete time : " + future.get().getUpdateTime());
    

        return "Success";
}

public String getUrl(String shortUrl) throws InterruptedException, ExecutionException {
    final Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    //String generatedId=this.randomStringGen();

    //asynchronously retrieve multiple documents
ApiFuture<QuerySnapshot> future =
    db.collection("cards").whereEqualTo("shortUrl", shortUrl).get();
// future.get() blocks on response
List <QueryDocumentSnapshot> documents = future.get().getDocuments();
String urlToHit="";
for (DocumentSnapshot document : documents) {
    urlToHit=document.getString("url");
  System.out.println(document.getString("url"));

}

    

        return urlToHit;
}

public String createGroupService(Map docData) throws InterruptedException, ExecutionException {
    final Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    //String generatedId=this.randomStringGen();

    
    String generatedId=this.randomStringGen();

    ApiFuture<WriteResult> future = db.collection("groups").document(generatedId).set(docData);

    System.out.println("Update time : " + future.get().getUpdateTime());
    
    

        return "Updated";
}

}
