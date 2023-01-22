import { render, screen } from '@testing-library/react';
import BudgetItemForm from './BudgetItemForm';
import userEvent from '@testing-library/user-event';

describe('BudgetItemForm component', () => {
  it('should render the Submit button component when clicked', async () => {
    render(<BudgetItemForm />);
    const element = screen.getByRole('button');
    expect(element).toBeInTheDocument();
    await userEvent.click(element);
    const submitButton = screen.getByRole('button', {
      name: /Submit/,
    } );
    expect(submitButton).toBeInTheDocument();
  });

  it('should not render Submit button when not clicked', () => {
    render(<BudgetItemForm/>);
    const submitButton = screen.queryByRole('button', {
      name: /Submit/,
    } );
    expect(submitButton).not.toBeInTheDocument();
  });
});
