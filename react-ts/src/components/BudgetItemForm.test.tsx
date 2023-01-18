import { render, screen } from '@testing-library/react';
import BudgetItemForm from './BudgetItemForm';
describe('BudgetItemForm component', () => {
  it('should render the Submit button component correctly', () => {
    render(<BudgetItemForm />);
    const element = screen.getByText('Submit');
    expect(element).toBeInTheDocument();
  });
});
