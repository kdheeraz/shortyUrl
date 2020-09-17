import firebase from "firebase";
const firebaseApp = firebase.initializeApp({
    apiKey: "AIzaSyBC19YQx3u7pYISqbJVv48SHAsDwSlTaVM",
    authDomain: "insta-clone-bd038.firebaseapp.com",
    databaseURL: "https://insta-clone-bd038.firebaseio.com",
    projectId: "insta-clone-bd038",
    storageBucket: "insta-clone-bd038.appspot.com",
    messagingSenderId: "83213632121",
    appId: "1:83213632121:web:6c7cd8dc96bf0865cc4aa8",
    measurementId: "G-LW8QFBHLDH"
  });

  const db=firebaseApp.firestore();
  const auth=firebase.auth();
  const storage=firebase.storage();

  export  {db,auth,storage};