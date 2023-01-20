import React from 'react';
import CalendarWrapper from './components/CalendarWrapper';
import BudgetItemForm from './components/BudgetItemForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

function App() {
  return (

              <div className="App">
                  <CalendarWrapper />
                  <BudgetItemForm />
              </div>

  );
}

export default App;
