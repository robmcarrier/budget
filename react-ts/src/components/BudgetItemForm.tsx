import { Button, Form } from 'react-bootstrap';
import BudgetItemsApi from '../api/budgetItemsApi';
import { useState } from 'react';
import BudgetItem from '../types/BudgetItem';
import { AxiosResponse } from 'axios';

const BudgetItemForm = () => {
  const [budgetItems, setBudgetItems]: [BudgetItem[], (budgetItems: BudgetItem[]) => void] = useState<BudgetItem[]>([]);
  const budgetItemsApi = new BudgetItemsApi();

  const doGet = () => {
    budgetItemsApi.getAll().then((resp: AxiosResponse<BudgetItem[]>) => {
      setBudgetItems(resp.data);
    });
  };

  return (
        <>
            <h3>Create Budget Item</h3>
            <Form>
                <Form.Group className="mb-3" controlId="name">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter Bill name"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="amount">
                    <Form.Label>Amount</Form.Label>
                    <Form.Control type="text" placeholder="Enter Amount"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Button onClick={() => doGet()} >Click ME</Button>
                {budgetItems.length > 0 ? <p>found some</p> : <p>found none</p>}
            </Form>
        </>

  );
};

export default BudgetItemForm;