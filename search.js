import React from 'react';
//import {data} from './data';
//import {newdata} from './newdata';
import DisplayCard from './DisplayCard';
import 'antd/dist/antd.css';
import { Card } from 'antd';
import { Button,Input } from 'antd';
const { Search } = Input;






class search extends React.Component {

  
  onSearch = value => {
    if(value==="")
    return
    const link=this.url + value
    this.setState({ loading: true},()=>
    fetch(link)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            loading: false,
            text: result.text.searchHits,
            title: result.title.searchHits,
            heading: result.heading.searchHits,
            images: result.images.searchHits,
          });
	}
      ),
      (error) => {
         console.log(error);
      }
)
}

onBackClick = (value) =>{
  this.props.history.push('/Crawl')
}

constructor(props)
{
    super(props);
    this.state = {
      loading: false,
      text:[],
      title:[],
      heading:[],
      images:[], 
    };
    this.url = "http://localhost:8080/mySpider/search/";
    this.onSearch = this.onSearch.bind(this);
    this.onBackClick = this.onBackClick.bind(this);
}

  render() {
    const{text,title,heading,images,loading} = this.state;
    const searchcount = text.length + title.length + heading.length + images.length;
    const cardtitle = "Search Results: " + searchcount;
  return (
    <div>
      <Button type="primary" danger onClick={this.onBackClick} style={{ margin: '20px 0px 0px 20px' }}>Back</Button>
      <Search placeholder="input search text" onSearch={this.onSearch} enterButton allowClear loading={loading} style={{ width: 500, margin: '10px 600px' }}/>
      <Card title={cardtitle}>
      {text.map((m)=> <DisplayCard link={m.content.link} parent={m.content.parentUrl} highlights={m.highlightFields.text} tag="Text" color="#f50" ></DisplayCard>)}
        {title.map((m)=> <DisplayCard link={m.content.link} parent={m.content.parentUrl} highlights={m.highlightFields.title} tag="Title" color="#2db7f5"></DisplayCard>)}
        {heading.map((m)=> <DisplayCard link={m.content.link} parent={m.content.parentUrl} highlights={m.highlightFields.heading} tag="Heading" color="#87d068"></DisplayCard>)}
        {images.map((m)=> <DisplayCard link={m.content.link} parent={m.content.parentUrl} highlights={m.highlightFields.images} tag="Images" color="#108ee9"></DisplayCard>)}
      </Card>
    </div>
    
  );
}
}
export default search;

