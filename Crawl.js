import React from 'react';
import 'antd/dist/antd.css';
import './index.css';
import { Button,Input } from 'antd';
import { Route } from 'react-router-dom'

import Logo from "./gecko-clipart-animal-crawl.png"

//import { withRouter } from 'react-router-dom'
const { Search } = Input;


class Crawl extends React.Component {

    constructor(props)
    {

        super(props);
        this.state = {
          loading: false,
        }
        this.url = "http://localhost:8080/mySpider/search/";
        this.onSearch = this.onSearch.bind(this);
        this.onNextClick = this.onNextClick.bind(this);
    }
    
    onSearch = (value) => {
      if(value=== "")
      return 
      this.setState({ loading: true},()=>{
      const requestOptions = { method: 'POST', body: value};
      fetch('http://localhost:8080/mySpider', requestOptions) 
      .then(response => response.json()) 
      .then(  (result) => {console.log(result);
        this.setState({
          loading: false
        });
      }
        );
      }
  )}          

      onNextClick = (value) =>{
        this.props.history.push('/search')
      }
  render() {
    const mystyle = {
      marginLeft: "600px",
    marginTop: "100px",
    position:"absolute"
    };

    const mystyle1 = {
      position:'fixed'
    };
    

  return (
    <div >
      <img style ={mystyle} src={Logo} alt="website logo" />
      <Button type="primary" danger onClick={this.onNextClick}  style={{position:"fixed",marginTop:"20px", marginLeft:"1800px" }}>search Page</Button>

    <Search placeholder="Please enter url" onSearch={this.onSearch} enterButton="Crawl" loading={this.state.loading} allowClear size="large" style={{ width: 700, margin: '400px 600px', position:'absolute' }}/>
  
    </div>
  );
    }
}
export default Crawl;

