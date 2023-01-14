import http from './http-common';
class BudgetItemsApi {
  getAll = () => {
    return http.get<Array<BudgetItems>>('url');
  };
}