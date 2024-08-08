import { act, render, screen } from '@testing-library/react';
import BudgetItemForm from './BudgetItemForm';
import userEvent from '@testing-library/user-event';
import { afterEach, expect, vi } from 'vitest';
import budgetItemsApi from '../api/budgetItemsApi';
import BudgetItem from '../types/BudgetItem';
// import BudgetItemsApi from '../api/budgetItemsApi';

describe('BudgetItemForm component', () => {
  it('should render Create Bill button', () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const createBill = screen.getByRole('button');
    expect(createBill).toBeInTheDocument();
  });
  it('should render the Submit button component when clicked', async () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const createBill = screen.getByRole('button');
    await userEvent.click(createBill);
    const submitButton = screen.getByRole('button', {
      name: /Submit/,
    });
    expect(submitButton).toBeInTheDocument();

  });
  it('should render the Click ME button when clicked', async () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const createBill = screen.getByRole('button');
    await userEvent.click(createBill);
    const clickMe = screen.getByRole('button', {
      name: /Click ME/,
    });
    expect(clickMe).toBeInTheDocument();
  });
  it('should render the header when clicked', async () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const createBill = screen.getByRole('button');
    await userEvent.click(createBill);
    const header = screen.getByRole('heading', {
      name: /Create Budget Item/,
    });
    expect(header).toBeInTheDocument();
  });
  it('should not render Submit button when not clicked', () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const submitButton = screen.queryByRole('button', {
      name: /Submit/,
    });
    expect(submitButton).not.toBeInTheDocument();
  });
});

const budgetItem: BudgetItem = {
  id: '1234',
  name: 'Test',
  amount: 123,
  dayOfMonth: 31,
};

vi.mock('../api/budgetItemsApi', () => {
  return {
    default: vi.fn(() => {
      return {
        getAll: vi.fn(() => {
          return Promise.resolve({
            data: {
              data: {
                budgetItems: [budgetItem],
              },
            },
          });
        }),
        createBudgetItem: vi.fn((budgetItemParam: BudgetItem) => {
          return Promise.resolve({
            data: {
              data: {
                budgetItem: budgetItemParam,
              },
            },
          });
        }),
      };
    }),
  };
});

describe('BudgetForm Click ME button', () => {
  it('should list things when it does the thing', async () => {
    act(() => {
      render(<BudgetItemForm/>);
    });
    const createBill = screen.getByRole('button');
    await userEvent.click(createBill);
    const clickMe = screen.getByRole('button', {
      name: /Click ME/,
    });

    await userEvent.click(clickMe);
    
    const firstBudgetItem = screen.getByTestId('1234');

    expect(firstBudgetItem).toBeInTheDocument();
  });

  describe('BudgetForm Submit Button', () => {
    afterEach(() => {
      vi.resetAllMocks();
    });
    it('should send request to create and doGet', async () => {
      act(() => {
        render(<BudgetItemForm/>);
      });
      const createBill = screen.getByRole('button');
      await userEvent.click(createBill);
      const submitButton = screen.getByRole('button', {
        name: /Submit/,
      });
      const nameField = screen.getByRole('textbox', {
        name: /Name/,
      });
      const amountField = screen.getByRole('spinbutton', {
        name: /Amount/,
      });
      const dayOfMonthField = screen.getByRole('spinbutton', {
        name: /Day of the month/,
      });

      await userEvent.type(nameField, 'Test');
      await userEvent.type(amountField, '123');
      await userEvent.type(dayOfMonthField, '31');

      await userEvent.click(submitButton);

      expect(budgetItemsApi).toHaveBeenCalled();

      const firstBudgetItem = screen.getByTestId('1234');

      expect(firstBudgetItem).toBeInTheDocument();
    });
    it('should not submit if fields aren\'t filled in', async () => {
      act(() => {
        render(<BudgetItemForm/>);
      });
      const createBill = screen.getByRole('button');
      await userEvent.click(createBill);
      const submitButton = screen.getByRole('button', {
        name: /Submit/,
      });

      await userEvent.click(submitButton);

      const firstBudgetItem = screen.queryByTestId('1234');

      expect(firstBudgetItem).not.toBeInTheDocument();
      expect(budgetItemsApi).toBeCalledTimes(2);
    });
  });
});
