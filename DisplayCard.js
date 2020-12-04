import React from 'react';
import 'antd/dist/antd.css';
import {Tag,Card} from 'antd';


class DisplayCard extends React.Component { 
    render(){
        return(
        <>
            <Card
                style={{ marginTop: 16 }}
                type="inner"
                title={<a href={this.props.link}>{this.props.link}</a>}
                extra={<Tag color={this.props.color}>{this.props.tag}</Tag>}
            >
                <h4><b>Parent Url:</b> <a href={this.props.parent}>{this.props.parent}</a></h4>
                <h5><b>Highlighted Fields:</b></h5>
                {this.props.highlights.map((m)=><div dangerouslySetInnerHTML={{__html:m}}></div>)}
            </Card>
          
        </>
        );
    }
}

export default DisplayCard
