import React,{useState,useEffect} from 'react';
import { auth } from 'firebase';
import logo from './logo.svg';
import './App.css';
import Card from './cards'
import './CardC.css';
import Modal from '@material-ui/core/Modal';
import { makeStyles } from '@material-ui/core/styles';
//import { Container,Button,Navbar,Nav,NavDropdown,Form,FormControl,Table } from 'react-bootstrap';

import { Column } from "react-bootstrap-grid-component/dist/Column";
import { Row } from "react-bootstrap-grid-component/dist/Row";
import { Select,Button, Input, form, Container } from '@material-ui/core';

useEffect(()=>{
    const unsubscribe=auth().onAuthStateChanged((authUser)=>{
      if (authUser){
        console.log(authUser)
        setUser(authUser)
        if(authUser.displayName){
          //props.loggedIn(user)
          //don't update username

        }
        else{
          //if we just created
          return authUser.updateProfile({
            displayName:username
          })
        }

      }
      else{
        //user logged out
        setUser(null)

      }
    })

    return()=>{
      
      //perform some clean up action than refire the useEffect
      unsubscribe();
    }
  },[user,username])////[user,username]  dependency


  const signUp=(event)=> {
    event.preventDefault();
    auth().createUserWithEmailAndPassword(email,password)
    .then((authUser)=>{
      authUser.user.updateProfile({
        displayName:username
      })
  
    })
    .catch((error)=>alert(error.message));
  };
  const resetField=()=>{
    setOpenSignIn(false)
    setOpenSignUp(false)
    setUsername()
    setPassword()
    setEmail()
  }
  const signIn=(event)=>{
    event.preventDefault();
    auth()
    .signInWithEmailAndPassword(email,password)
    .catch((error)=>{
      console.log(error)
      alert(error.code)
    })
    setOpenSignIn(false)
    //props.refresh()
    props.loggedIn(user)
    //window.location.reload(false)//on sign in close the modal
  }

  const signOut=()=>{

    auth().signOut()
    //window.location.reload(false)

  }

  
