package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Days;
import org.joda.time.Hours;

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

    DateTime dateTime=new DateTime();
    String id="";
    
ApiFuture<QuerySnapshot> future =
    db.collection("cards").whereEqualTo("shortUrl", shortUrl).get();
// future.get() blocks on response
List <QueryDocumentSnapshot> documents = future.get().getDocuments();
String urlToHit="";
long expTime=0;
long minutes=0;
for (DocumentSnapshot document : documents) {
    urlToHit=document.getString("url");
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    expTime=(long)document.get("expireThresh");
    id=document.getId();

	Date d1 = null;
	Date d2 = null;

	try {
		d1 = format.parse(document.getString("updateTime"));
		d2 = format.parse(dateTime.now().toString("MM/dd/yyyy HH:mm:ss"));

		DateTime dt1 = new DateTime(d1);
		DateTime dt2 = new DateTime(d2);



        minutes=(Days.daysBetween(dt1, dt2).getDays()*24*60+(Hours.hoursBetween(dt1, dt2).getHours() % 24)*60+ Minutes.minutesBetween(dt1, dt2).getMinutes() % 60);
        System.out.println(minutes);
        

    }
    catch (Exception e) {
		e.printStackTrace();
	 }


}

if(minutes>expTime || minutes>60){
    this.deleteDetails(id);
    return "Short Link has expired";
}
else{

    return urlToHit;

}

    

      
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
