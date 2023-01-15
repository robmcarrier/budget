import React from 'react';
import './App.css';
import CalendarWrapper from './components/CalendarWrapper';
import BudgetItemForm from './components/BudgetItemForm';

function App() {
  return (
    <div className="App">
      <CalendarWrapper />
        <BudgetItemForm />
    </div>
  );
}

export default App;
