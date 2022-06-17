import React from "react";
import {useState} from "react";

const x = document.cookie;

class Maindetail extends React.Component{
    
    
constructor(props){
super(props)
this.state ={
    list: []
}
this.callApi=this.callApi.bind(this)
this.callApi();

 
console.log(x)
}


callApi(){

fetch("http://localhost:8080/api/game/by?id="+x).then(
(response) => response.json()
).then((data)=>{
    console.log(data)
    this.setState({
        list:[data]
    })
})


}

render(){
    

   
    let tb_data = this.state.list.map((item)=>{
        var zmienn=""
        if(item.teams[0].result==1){
            zmienn="Zwyciezca"
        }
        else{
            zmienn="przegrany"
        }
        return(
            
        <tr key={item.id}>
        <td>{zmienn}</td>
        <td>{item.league}</td>
        <td>{item.teams[0].name}</td>
        <td>{item.teams[0].ban1}
        <br/>
        {item.teams[0].ban2}
        <br/>
        {item.teams[0].ban3}
        <br/>
        {item.teams[0].ban4}
        <br/>
        {item.teams[0].ban5}
        </td>
        <td>
        {"Gracz "+item.teams[0].players[0].name+" Pozycja "}
            {item.teams[0].players[0].position+" Zagrał "}
            {item.teams[0].players[0].champion}
            <br/>
            {"Gracz "+item.teams[1].players[1].name+" Pozycja "}
            {item.teams[0].players[1].position+" Zagrał "}
            {item.teams[0].players[1].champion}
            <br/>
            {"Gracz "+item.teams[1].players[2].name+" Pozycja "}
            {item.teams[0].players[2].position+" Zagrał "}
            {item.teams[0].players[2].champion}
            <br/>
            {"Gracz "+item.teams[1].players[3].name+" Pozycja "}
            {item.teams[0].players[3].position+" Zagrał "}
            {item.teams[0].players[3].champion}
            <br/>
            {"Gracz "+item.teams[1].players[4].name+" Pozycja "}
            {item.teams[0].players[4].position+" Zagrał "}
            {item.teams[0].players[4].champion}
            <br/>
        </td>
        
          
       
        
            
        


        </tr>   

        )


    
    
    }

    
    
    
    
    
    
    )
    
    let tb_data2 = this.state.list.map((item)=>{
        var zmienn=""
        if(item.teams[1].result==1){
            zmienn="Zwyciezca"
        }
        else{
            zmienn="przegrany"
        }
        return(
            
        <tr key={item.id}>
        <td>{zmienn}</td>
        <td>{item.league}</td>
        <td>{item.teams[1].name}</td>
        <td>{item.teams[1].ban1}
        <br/>
        {item.teams[1].ban2}
        <br/>
        {item.teams[1].ban3}
        <br/>
        {item.teams[1].ban4}
        <br/>
        {item.teams[1].ban5}
        </td>
        <td>
            {"Gracz "+item.teams[1].players[0].name+" Pozycja "}
            {item.teams[1].players[0].position+" Zagrał "}
            {item.teams[1].players[0].champion}
            <br/>
            {"Gracz "+item.teams[1].players[1].name+" Pozycja "}
            {item.teams[1].players[1].position+" Zagrał "}
            {item.teams[1].players[1].champion}
            <br/>
            {"Gracz "+item.teams[1].players[2].name+" Pozycja "}
            {item.teams[1].players[2].position+" Zagrał "}
            {item.teams[1].players[2].champion}
            <br/>
            {"Gracz "+item.teams[1].players[3].name+" Pozycja "}
            {item.teams[1].players[3].position+" Zagrał "}
            {item.teams[1].players[3].champion}
            <br/>
            {"Gracz "+item.teams[1].players[4].name+" Pozycja "}
            {item.teams[1].players[4].position+" Zagrał "}
            {item.teams[1].players[4].champion}
            <br/>
        </td>
        
          
       
        
            
        


        </tr>   

        )


    
    
    }

    
    
    
    
    
    
    )


    return(
        <div className="container">
            <div>
   
 
            </div>

            <div class="text-center" >
            <table className="table table-bordered" >
            <thead className="thead-dark">
                <tr>
                    <th>Wynik</th>
                    <th>Liga</th>
                    <th>Drużyna 1</th>
                    <th>Bany</th>
                    <th>Gracze</th>
                    
                </tr>
            </thead>
            <tbody>
                {tb_data}
            
            </tbody>


            </table>


            <table className="table table-bordered" >
            <thead className="thead-dark">
                <tr>
                    <th>Wynik</th>
                    <th>Liga</th>
                    <th>Drużyna 2</th>
                    <th>Bany</th>
                    <th>Gracze</th>
                    
                </tr>
            </thead>
            <tbody>
                {tb_data2}
            
            </tbody>


            </table>


            </div>
        </div>
        
    );
}






}

export default Maindetail;