import { Button, Form } from 'react-bootstrap';
import BudgetItemsApi from '../api/budgetItemsApi';
import { FormEvent, useState } from 'react';
import BudgetItem from '../types/BudgetItem';
import { AxiosResponse } from 'axios';
import GetBudgetItemsRequest from '../types/GetBudgetItemsRequest';

const BudgetItemForm = () => {
  const [budgetItems, setBudgetItems] = useState<BudgetItem[]>([]);
  const [name, setName] = useState('');
  const [amount, setAmount] = useState<number>(0);
  const [dayOfMonth, setDayOfMonth] = useState<number>(0);
  const budgetItemsApi = new BudgetItemsApi();
  const [validated, setValidated] = useState(false);
  const [show, setShow] = useState(false);

  const doGet = () => {
    budgetItemsApi.getAll().then((resp: AxiosResponse<GetBudgetItemsRequest>) => {
      setBudgetItems(resp.data.data.budgetItems);
    });
  };

  const submit = (e: FormEvent<HTMLFormElement>) => {
    if (!e.currentTarget.checkValidity()) {
      e.preventDefault();
      e.stopPropagation();
      return;
    }

    setValidated(true);
    e.preventDefault();
    e.stopPropagation();
    let budgetItem: BudgetItem = {
      amount: amount,
      name: name,
      dayOfMonth: dayOfMonth,
    };
    budgetItemsApi.createBudgetItem(budgetItem);
    setName('');
    setAmount(0);
    setDayOfMonth(0);
    doGet();

  };

  return (
    <>
      <Button onClick={() => setShow(!show)}>Create Bill</Button>
      {show &&
        (
          <>
          <h3>Create Budget Item</h3>
        <Form noValidate validated={validated} onSubmit={(event) => submit(event)}>
          <Form.Group className="mb-3" controlId="name">
            <Form.Label>Name</Form.Label>
            <Form.Control required type="text" placeholder="Enter Bill name" value={name}
                          onChange={(e) => setName(e.target.value)}/>
          </Form.Group>
          <Form.Group className="mb-3" controlId="amount">
            <Form.Label>Amount</Form.Label>
            <Form.Control type="number" placeholder="Enter Amount" value={amount}
                          onChange={(e) => setAmount(parseInt(e.target.value))}/>
          </Form.Group>
          <Form.Group className={'mb-3'} controlId={'dayOfMonth'}>
            <Form.Label>Day of the month</Form.Label>
            <Form.Control type={'number'} placeholder={'Enter day of the month'} value={dayOfMonth}
                          onChange={(e) => setDayOfMonth(parseInt(e.target.value))}/>
          </Form.Group>
          <Button variant="primary" type="submit">
            Submit
          </Button>
          <Button onClick={() => doGet()}>Click ME</Button>
          {budgetItems.length > 0 ? (
            budgetItems.map((budgetItem) => {
              return <p key={budgetItem.id} data-testid={budgetItem.id}>{budgetItem.name}</p>;
            })
          ) : <p>None found</p>}
        </Form>
          </>
        )
      }

    </>

  );
};

export default BudgetItemForm;