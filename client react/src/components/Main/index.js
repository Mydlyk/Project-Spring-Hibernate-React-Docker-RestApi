import styles from "./styles.module.css"
import React, { useEffect, useState } from 'react';
import { Button, Modal, ModalTitle } from 'react-bootstrap'
import axios from 'axios'
import _ from "lodash";


    /*
            <button className={styles.white_btn} onClick={handleLogout}>
                Wyloguj się
            </button>
            */
           
            
            const pageSize =20;
            const Main =()=>{

              /*
             
          
          
                        // This is how to define an asynchronous function with a Promise.
                        MyPromiseFunction: function(args) {
                            return new Promise((resolve) => {
                              // do some work
                              resolve({
                                damage: args.damage
                                time: args.time
                              });
                            });
                        },
          
                        // This is how to receive incoming headers
                        HeadersAwareFunction: function(args, cb, headers) {
                            return {
                                name: headers.Token
                                time: headers.Token
                            };
                        },
          
                        // You can also inspect the original `req`
                        reallyDetailedFunction: function(args, cb, headers, req) {
                            console.log('SOAP `reallyDetailedFunction` request from ' + req.connection.remoteAddress);
                            return {
                                name: headers.Token
                                time: headers.Token
                            };
                        }
                    }
                }
            };
          
            var xml = require('fs').readFileSync('myservice.wsdl', 'utf8');
          
          
          
            
            });
          
          
                });
            });*/
                
              const [posts,setposts]=useState([]);
              const [paginatedPosts,setpaginatedPosts]=useState([]);
              const [currentPage,setcurrentPage]=useState([1]);

              const handleLogout = () => {
                localStorage.removeItem("token")
                window.location.reload()
                }
            useEffect(()=>{
            
            axios.get('http://localhost:8080/api/game')
            .then(res=>{
              console.log(res.data);
              setposts(res.data);
              setpaginatedPosts(_(res.data).slice(0).take(pageSize).value())
            })
            },[])
            
            
            const pageCount = posts? Math.ceil(posts.length/pageSize):0;
            if (pageCount ===1)return null;
            const pages = _.range(1,pageCount+1)
            const pagination=(pageNo)=>{
            
            
              setcurrentPage(pageNo);
              const startIndex=(pageNo -1)*pageSize;
              const paginatedPost=_(posts).slice(startIndex).take(pageSize).value();
              setpaginatedPosts(paginatedPost)
            }
            let tb_data = paginatedPosts.map((item,index)=>{
              var a="http://localhost:8080/api/game/by?id="+String(item.id)
              var b=String(item.id)
              return(
                  
              <tr key={index}>
              <td>{item.id}</td>
              <td>{item.league}</td>
              <td>{item.teams[0].name}</td>
              <td>{item.teams[1].name}
            
              </td>
              
                  <td>
                   
              <a class="link-info" href={a}>Szczegóły back</a>
              <br/>
              <a class="link-success" href="http://localhost:3000/Maindetail" onClick={document.cookie = b}>Szczegóły front</a>

              </td>
             
              
                  
              
            
            
              </tr>   
            
              )
           
            
            })
            return(
              
              <div className="container">
                  <div>
                  <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" onClick={handleLogout}>Wyloguj</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="http://localhost:8080/api/game/json/export">Eksport json<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="http://localhost:8080/api/game/json/import">Import json</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="http://localhost:8080/api/game/xml/export">Eksport xml</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="http://localhost:8080/api/game/xml/import">Import xml</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="http://localhost:7779/ws/soap">Soup</a>
            </li>
            
            </ul>
            <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            </div>
            </nav>
            
                  </div>
                  
            
                  <div class="text-center" >
                  <table className="table table-bordered" >
                  <thead className="thead-dark">
                      <tr>
                          <th>ID</th>
                          <th>Liga</th>
                          <th>Drużyna 1</th>
                          <th>Drużyna 2</th>
                          <th></th>
                          
                      </tr>
                  </thead>
                  <tbody>
                      {tb_data}
                      
                  </tbody>
            
            
                  </table>
                  
                  </div>
                  <div>
                  <nav className="d-flex justify-content-center">
                    <ul className="paginaton">
                      {
            pages.map((page)=>(
            
            <li className={
            page=== currentPage? "page-item active":"page-item"
            
            }><p className="page-link" onClick={()=>pagination(page)}>{page}</p></li>
                   
            ))
            
                      }
                      
            
                    </ul>
                  </nav>
                  </div>
              
                  </div>
                  
            
              
              
            );
            
            
            
            
            
            
            
            };
            export default Main;