import http from './http-common';
import BudgetItem from '../src/types/BudgetItem';
class BudgetItemsApi {
  getAll = () => {
    return http.get<Array<BudgetItem>>('url');
  };
}