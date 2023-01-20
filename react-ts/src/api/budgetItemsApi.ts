import http from './http-common';
import BudgetItem from '../types/BudgetItem';
import GetBudgetItemsRequest from '../types/GetBudgetItemsRequest';

class BudgetItemsApi {
  getAll = () => {
    return http.post<GetBudgetItemsRequest>('/graphql', {
      query: `
        query {
          budgetItems{
            id
            name
          }
        }`,
    });
  };

  createBudgetItem = (budgetItem: BudgetItem) => {
    return http.post<BudgetItem>('/graphql', {
      query: `
        mutation($name: String! $amount: Int $dayOfMonth: Int $split: Boolean) {
          createBudgetItem(budgetItem: {
            name: $name, amount: $amount, dayOfMonth: $dayOfMonth split: $split}) {
            id
            name
          }
        }
      `,
      variables: budgetItem,
    });
  };
}

export default BudgetItemsApi;