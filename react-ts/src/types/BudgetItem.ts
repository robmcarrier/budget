import { Key } from 'react';

export default interface BudgetItem {
  id?: Key,
  name: String,
  amount: Number | undefined,
  dayOfMonth: Number | undefined,
  split?: boolean,
}
