import React from 'react';
import Interface from './search';
import Crawl from './Crawl';
import {
  Route,Switch
} from "react-router-dom";

class App extends React.Component {
  
  render() {
  return (
    <div>
    <Switch>
    <Route path="/search" component={Interface}></Route>
    <Route path="/" component={Crawl}></Route>
    <Route path="/Crawl" component={Crawl}></Route>
    </Switch>
    </div>
  );
    }
}
export default App;

