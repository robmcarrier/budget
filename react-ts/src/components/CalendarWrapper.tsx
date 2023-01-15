import React, { useState } from 'react';
import Calendar from 'react-calendar';

const CalendarWrapper = () => {
  const [value, onChange] = useState(new Date());
  return (
        <>
            <h1>Calendar Title</h1>
            <p>cool!</p>
            <Calendar value={value} onChange={onChange} />
        </>
  );
};

export default CalendarWrapper;