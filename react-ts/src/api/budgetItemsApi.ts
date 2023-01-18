import http from './http-common';
import BudgetItem from '../types/BudgetItem';

class BudgetItemsApi {
  getAll = () => {
    return http.post<BudgetItem[]>('/graphql', {
      query: `
        query {
          budgetItems{
            id
            name
          }
        }`,
    });
  };
}

export default BudgetItemsApi;