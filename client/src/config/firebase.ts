import firebase from 'firebase';

const firebaseConfig = {
    apiKey: 'AIzaSyAPzE0qA2f05-r_NCx57MTBJuCSSSi5yKc',
    authDomain: 'brew-log-166f0.firebaseapp.com',
    databaseURL: 'https://brew-log-166f0.firebaseio.com',
    projectId: 'brew-log-166f0',
    storageBucket: 'brew-log-166f0.appspot.com',
    messagingSenderId: '674089166660'
};

export const googleProvider = new firebase.auth.GoogleAuthProvider();

export default firebaseConfig;