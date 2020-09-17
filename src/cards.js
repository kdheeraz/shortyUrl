import React,{useState} from 'react';
import "./CardC.css"
import 'bootstrap/dist/css/bootstrap.css';



import Tooltip from '@material-ui/core/Icon';
//import DeleteIcon from '@material-ui/core/Icon';
//import DeleteTwoToneIcon from '@material-ui/icons/DeleteTwoTone';
//import { Button,Navbar,Nav,NavDropdown,Form,FormControl,Table } from 'react-bootstrap';

//import IconButton from '@material-ui/core/IconButton';
import {Button} from '@material-ui/core/'

import { makeStyles } from '@material-ui/core/styles';

import DeleteIcon from '@material-ui/icons/Delete';


function Card(props) {



//     const xx=()=>{return((<IconButton aria-label="delete">
//     <DeleteIcon fontSize="small" />
//   </IconButton>))}

    const useStyles = makeStyles((theme) => ({
        button: {
          margin: theme.spacing(1),
        },
      }));

    const classes = useStyles();

    
    const [title,setTitle]= useState(props.user)
    const[flag,setFlag]=useState(true)
    const[user,setUser]=useState(props.user)
    const[cross,setCross]=useState(true)
    const[disc,setDisc]=useState("Description")
    const [oid,setoid]= useState()
    const [isButton,setButton]= useState("btzero")

    //alert(props.logg)



    //const[descPart,setdescPart]=useState(props.description.subString(4,10))
     //var desc2=props.description.substr(4,10)

     

    

   const Disc=()=>{
       console.log("the key"+props.key)
        setDisc(props.description)
    }
    const revdesc=()=>{
        setDisc("Description")

    }


   const changei=(event)=>{
       event.preventDefault()
       
       setFlag(!flag);
if(flag){
    setTitle(props.user)
    
}
else{
    setCross(false)
}}

const changec=(event)=>{
    event.preventDefault()
       
       setCross(!cross);
}


const test=()=>{

    props.upf(props.user,props.ID);



}
//alert(props.user.group)
//alert(props.fil)
if(cross && (props.user.group===props.fil || props.fil==="" || props.fil==="All")){
    return (
        
        <div className="cardcss">
            
            <div className="cardTitle">{props.user.title}</div>
            <hr></hr>
            
            {/* <div className="cardTitle" onMouseEnter={Disc}  onMouseOut={revdesc} onClick={"http://google.com"}> */}

            {/* <div className="cardTitle"> */}
    <div data-toggle="tooltip" title={props.description}><a  href={"http://localhost:8080/api/v1"+"/"+
    props.user.shortUrl} add target="_blank">Description:{props.description.substr(0,25)+"...."}</a></div>

                {/* </div> */}


             {/* <button className="btn btn-" onClick={changei}>Check</button>  */}
              <button className="btn btn-outline-danger btn btn-sm bt" disabled={false}  onClick={(event)=>props.delfn(props.ID)}>Remove
             {/* <span class="glyphicon glyphicon-trash"></span> */}
             </button>  
            {true && <button className="btn btn-outline-warning btn btn-sm bt" disabled={false}  onClick={(event)=>props.upf(props.user,props.ID)}
                 >  Edit
             {/* <span class="glyphicon glyphicon-trash"></span> */}
             </button>}

{/* <div>
            
      <IconButton aria-label="delete">
          <DeleteIcon fontSize="small" />
        </IconButton>)
                 </div>


             
            <xx></xx> */}
        </div>
      );
    
}
else{
    return(
    <div></div>
    )
}

}    
    
  
  export default Card;
  