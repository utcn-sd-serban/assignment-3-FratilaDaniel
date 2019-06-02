import React from 'react';
import './App.css';

import { HashRouter, Switch, Route } from "react-router-dom";
import SmartQuestionsList from './view/SmartQuestionsList';
import SmartCreateQuestion from './view/SmartCreateQuestion';

const App = () => (
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact={true} component={SmartQuestionsList} path="/" />
        <Route exact={true} component={SmartCreateQuestion} path="/create-question" />
      </Switch>
    </HashRouter>
  </div>
);


export default App;
