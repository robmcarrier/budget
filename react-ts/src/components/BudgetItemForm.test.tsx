import { render, screen } from '@testing-library/react';
import BudgetItemForm from './BudgetItemForm';
import userEvent from '@testing-library/user-event';

describe('BudgetItemForm component', () => {
  it('should render the Submit button component correctly', async () => {
    render(<BudgetItemForm />);
    const element = screen.getByRole('button');
    expect(element).toBeInTheDocument();
    await userEvent.click(element);
    const submitButton = screen.getByRole('button', {
      name: /Submit/,
    } );
    expect(submitButton).toBeInTheDocument();
  });
});
