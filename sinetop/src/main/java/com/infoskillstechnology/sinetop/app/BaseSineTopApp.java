package com.infoskillstechnology.sinetop.app;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseSineTopApp extends Application {

  public final static String TAG = "SineTopApp";

  private FirebaseAnalytics mFirebaseAnalytics;

  private StorageReference mStorageRef;

  @Override
  public void onCreate() {
    super.onCreate();

//    FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//    mStorageRef = FirebaseStorage.getInstance().getReference();
//
//    // Obtain the FirebaseAnalytics instance.
//    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//    Bundle bundle = new Bundle();
//    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "");
//    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "");
//    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//
//    // Write a message to the database
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message");
//
//    myRef.setValue("Hello, World!");
//
//    // Read from the database
//    myRef.addValueEventListener(new ValueEventListener() {
//      @Override
//      public void onDataChange(DataSnapshot dataSnapshot) {
//        // This method is called once with the initial value and again
//        // whenever data at this location is updated.
//        String value = dataSnapshot.getValue(String.class);
//        Log.d(TAG, "Value is: " + value);
//      }
//
//      @Override
//      public void onCancelled(DatabaseError error) {
//        // Failed to read value
//        Log.w(TAG, "Failed to read value.", error.toException());
//      }
//    });
//
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    // Create a new user with a first and last name
//    Map<String, Object> user = new HashMap<>();
//    user.put("first", "Ada");
//    user.put("last", "Lovelace");
//    user.put("born", 1815);
//
//// Add a new document with a generated ID
//    db.collection("users")
//        .add(user)
//        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//          @Override
//          public void onSuccess(DocumentReference documentReference) {
//            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//          }
//        })
//        .addOnFailureListener(new OnFailureListener() {
//          @Override
//          public void onFailure(@NonNull Exception e) {
//            Log.w(TAG, "Error adding document", e);
//          }
//        });
//

  }


  void file() {

    Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
    StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

    riversRef.putFile(file)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
          @Override
          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            // Get a URL to the uploaded content
//            Uri downloadUrl = taskSnapshot.getDownloadUrl();
          }
        })
        .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception exception) {
            // Handle unsuccessful uploads
            // ...
          }
        });
  }


  void invite() {

  }
}
